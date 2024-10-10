package com.starter.core.domain.user.admin.service;

import com.starter.core.config.exception.ExceptionMessageType;
import com.starter.core.config.exception.NotAccessDataException;
import com.starter.core.config.exception.NotExistDataException;
import com.starter.core.domain.DomainServiceTest;
import com.starter.core.domain.user.AdminUserMock;
import com.starter.core.domain.user.admin.AdminUser;
import com.starter.core.domain.user.admin.AdminUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdminUserDomainServiceTest extends DomainServiceTest {

    @MockBean
    @InjectMocks
    private AdminUserDomainService adminUserDomainService;

    @Mock
    private AdminUserRepository adminUserRepository;

    @Test
    @DisplayName("관리자 회원 조회")
    void getAdminUser() {
        AdminUser savedAdminUser = AdminUserMock.createUser();
        given(adminUserRepository.findById(anyLong())).willReturn(Optional.of(savedAdminUser));
        AdminUser returnAdminUser1 = adminUserDomainService.getAdminUser(1L);
        assertThat(returnAdminUser1).isEqualTo(savedAdminUser);

        given(adminUserRepository.findById(anyLong())).willReturn(Optional.empty());
        Throwable exception = assertThrows(NotExistDataException.class,
                () -> adminUserDomainService.getAdminUser(1L));
        assertThat(exception.getMessage()).isEqualTo(ExceptionMessageType.NOT_EXIST_USER.getMessage());

        verify(adminUserRepository, times(2)).findById(anyLong());
    }

    @Test
    @DisplayName("관리자 회원 접근 권한 확인")
    void checkAccessAdminUser() {
        AdminUser savedAdminUser = AdminUserMock.createUser();
        given(adminUserRepository.findById(anyLong())).willReturn(Optional.of(savedAdminUser));
        adminUserDomainService.checkAccessAdminUser(1L);

        given(adminUserRepository.findById(anyLong())).willReturn(Optional.empty());
        Throwable exception1 = assertThrows(NotExistDataException.class,
                () -> adminUserDomainService.checkAccessAdminUser(1L));
        assertThat(exception1.getMessage()).isEqualTo(ExceptionMessageType.NOT_EXIST_USER.getMessage());

        AdminUser resignAdminUser = AdminUserMock.createResignUser();
        given(adminUserRepository.findById(anyLong())).willReturn(Optional.of(resignAdminUser));
        Throwable exception2 = assertThrows(NotAccessDataException.class,
                () -> adminUserDomainService.checkAccessAdminUser(1L));
        assertThat(exception2.getMessage()).isEqualTo(ExceptionMessageType.RESIGN_USER.getMessage());

        AdminUser blockAdminUser = AdminUserMock.createBlockUser();
        given(adminUserRepository.findById(anyLong())).willReturn(Optional.of(blockAdminUser));
        Throwable exception3 = assertThrows(NotAccessDataException.class,
                () -> adminUserDomainService.checkAccessAdminUser(1L));
        assertThat(exception3.getMessage()).isEqualTo(ExceptionMessageType.BLOCK_USER.getMessage());

        verify(adminUserRepository, times(4)).findById(anyLong());
    }

}