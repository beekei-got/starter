package com.starter.core.domain.user.admin;

import java.util.Optional;

public interface AdminUserRepository {
    Optional<AdminUser> findById(long userId);
}
