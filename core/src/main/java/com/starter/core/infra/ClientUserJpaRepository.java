package com.starter.core.infra;

import com.starter.core.domain.user.ClientUser;
import com.starter.core.domain.user.ClientUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientUserJpaRepository extends JpaRepository<ClientUser, Long>, ClientUserRepository {
}
