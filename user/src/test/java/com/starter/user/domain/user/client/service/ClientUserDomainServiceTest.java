package com.starter.user.domain.user.client.service;

import com.starter.user.application.dto.SaveClientUserParameterDTO;
import com.starter.user.config.payload.exception.ExceptionMessageType;
import com.starter.user.config.payload.exception.NotAccessDataException;
import com.starter.user.config.payload.exception.NotExistDataException;
import com.starter.user.domain.DomainServiceTest;
import com.starter.user.domain.user.ClientUserMock;
import com.starter.user.domain.user.Gender;
import com.starter.user.domain.user.client.ClientUser;
import com.starter.user.domain.user.client.ClientUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ClientUserDomainServiceTest extends DomainServiceTest {

    @MockBean
    @InjectMocks
    private ClientUserDomainService clientUserDomainService;

    @Mock
    private ClientUserRepository clientUserRepository;

    @Test
    @DisplayName("사용자 회원 조회")
    void getClientUser() {
        ClientUser savedClientUser = ClientUserMock.createUser();
        given(clientUserRepository.findById(anyLong())).willReturn(Optional.of(savedClientUser));
        ClientUser returnClientUser1 = clientUserDomainService.getClientUser(1L);
        assertThat(returnClientUser1).isEqualTo(savedClientUser);

        given(clientUserRepository.findById(anyLong())).willReturn(Optional.empty());
        Throwable exception = assertThrows(NotExistDataException.class,
            () -> clientUserDomainService.getClientUser(1L));
        assertThat(exception.getMessage()).isEqualTo(ExceptionMessageType.NOT_EXIST_USER.getMessage());

        verify(clientUserRepository, times(2)).findById(anyLong());
    }

    @Test
    @DisplayName("사용자 회원 접근 권한 확인")
    void checkAccessClientUser() {
        ClientUser clientUser = ClientUserMock.createUser();
        given(clientUserRepository.findById(anyLong())).willReturn(Optional.of(clientUser));
        clientUserDomainService.checkAccessClientUser(1L);

        given(clientUserRepository.findById(anyLong())).willReturn(Optional.empty());
        Throwable exception1 = assertThrows(NotExistDataException.class,
                () -> clientUserDomainService.checkAccessClientUser(1L));
        assertThat(exception1.getMessage()).isEqualTo(ExceptionMessageType.NOT_EXIST_USER.getMessage());

        ClientUser resignClientUser = ClientUserMock.createResignUser();
        given(clientUserRepository.findById(anyLong())).willReturn(Optional.of(resignClientUser));
        Throwable exception2 = assertThrows(NotAccessDataException.class,
                () -> clientUserDomainService.checkAccessClientUser(1L));
        assertThat(exception2.getMessage()).isEqualTo(ExceptionMessageType.RESIGN_USER.getMessage());

        ClientUser blockClientUser = ClientUserMock.createBlockUser();
        given(clientUserRepository.findById(anyLong())).willReturn(Optional.of(blockClientUser));
        Throwable exception3 = assertThrows(NotAccessDataException.class,
                () -> clientUserDomainService.checkAccessClientUser(1L));
        assertThat(exception3.getMessage()).isEqualTo(ExceptionMessageType.BLOCK_USER.getMessage());

        verify(clientUserRepository, times(4)).findById(anyLong());
    }

    @Test
    @DisplayName("사용자 회원 등록")
    void saveClientUser() {
        SaveClientUserParameterDTO parameter = SaveClientUserParameterDTO.builder().email("test@test.com").build();

        given(clientUserRepository.findByEmail(anyString())).willReturn(Optional.empty());
        ClientUser savedClientUser1 = ClientUserMock.createUser();
        given(clientUserRepository.saveAndFlush(any(ClientUser.class))).willReturn(savedClientUser1);
        ClientUser returnClientUser = clientUserDomainService.saveClientUser(parameter);
        assertThat(returnClientUser).isEqualTo(savedClientUser1);

        ClientUser savedClientUser2 = ClientUserMock.createUser();
        given(clientUserRepository.findByEmail(anyString())).willReturn(Optional.of(savedClientUser2));
        ClientUser returnClientUser2 = clientUserDomainService.saveClientUser(parameter);
        assertThat(returnClientUser2).isEqualTo(savedClientUser2);
    }

}