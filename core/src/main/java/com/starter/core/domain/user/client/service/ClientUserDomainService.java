package com.starter.core.domain.user.client.service;

import com.starter.core.config.exception.ExceptionMessageType;
import com.starter.core.config.exception.NotAccessDataException;
import com.starter.core.config.exception.NotExistDataException;
import com.starter.core.domain.user.UserStatus;
import com.starter.core.domain.user.client.ClientUser;
import com.starter.core.domain.user.client.ClientUserRepository;
import com.starter.core.domain.user.client.service.dto.SaveClientUserParameterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientUserDomainService {

    private final ClientUserRepository clientUserRepository;

    public ClientUser getClientUser(long userId) {
        return clientUserRepository.findById(userId)
            .orElseThrow(() -> new NotExistDataException(ExceptionMessageType.NOT_EXIST_USER));
    }

    public void checkAccessClientUser(long userId) {
        ClientUser clientUser = getClientUser(userId);
        if (clientUser.getStatus().equals(UserStatus.RESIGN))
            throw new NotAccessDataException(ExceptionMessageType.RESIGN_USER);
        if (clientUser.getStatus().equals(UserStatus.BLOCK))
            throw new NotAccessDataException(ExceptionMessageType.BLOCK_USER);
    }

    public ClientUser saveClientUser(SaveClientUserParameterDTO parameter) {
        return clientUserRepository.findByEmail(parameter.getEmail())
            .orElseGet(() -> clientUserRepository.saveAndFlush(ClientUser.createUser(
                parameter.getEmail(),
                parameter.getUserName(),
                parameter.getNickname(),
                parameter.getUserGender(),
                parameter.getUserBirthday(),
                parameter.getPhoneNumber()
            )));
    }

}
