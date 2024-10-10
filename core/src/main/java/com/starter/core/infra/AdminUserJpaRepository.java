package com.starter.core.infra;

import com.starter.core.domain.user.admin.AdminUser;
import com.starter.core.domain.user.admin.AdminUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserJpaRepository extends JpaRepository<AdminUser, Long>, AdminUserRepository {
}
