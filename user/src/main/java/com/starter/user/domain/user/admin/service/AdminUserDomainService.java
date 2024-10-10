package com.starter.user.domain.user.admin.service;

import com.starter.user.config.payload.exception.ExceptionMessageType;
import com.starter.user.config.payload.exception.NotAccessDataException;
import com.starter.user.config.payload.exception.NotExistDataException;
import com.starter.user.domain.user.UserStatus;
import com.starter.user.domain.user.admin.AdminUser;
import com.starter.user.domain.user.admin.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserDomainService {

    private final AdminUserRepository adminUserRepository;

    public AdminUser getAdminUser(long userId) {
        return adminUserRepository.findById(userId)
                .orElseThrow(() -> new NotExistDataException(ExceptionMessageType.NOT_EXIST_USER));
    }

    public void checkAccessAdminUser(long userId) {
        AdminUser adminUser = getAdminUser(userId);
        if (adminUser.getStatus().equals(UserStatus.RESIGN))
            throw new NotAccessDataException(ExceptionMessageType.RESIGN_USER);
        if (adminUser.getStatus().equals(UserStatus.BLOCK))
            throw new NotAccessDataException(ExceptionMessageType.BLOCK_USER);
    }

}
