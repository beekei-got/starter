package com.starter.core.domain.user.admin.service;

import com.starter.core.config.exception.ExceptionMessageType;
import com.starter.core.config.exception.NotAccessDataException;
import com.starter.core.config.exception.NotExistDataException;
import com.starter.core.domain.user.UserStatus;
import com.starter.core.domain.user.admin.AdminUser;
import com.starter.core.domain.user.admin.AdminUserRepository;
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
        if (adminUser.getUserStatus().equals(UserStatus.RESIGN))
            throw new NotAccessDataException(ExceptionMessageType.RESIGN_USER);
        if (adminUser.getUserStatus().equals(UserStatus.BLOCK))
            throw new NotAccessDataException(ExceptionMessageType.BLOCK_USER);
    }

}
