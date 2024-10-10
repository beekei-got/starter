package com.starter.user.infra;

import com.starter.user.domain.user.admin.AdminUser;
import com.starter.user.domain.user.admin.AdminUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserJpaRepository extends JpaRepository<AdminUser, Long>, AdminUserRepository {
}
