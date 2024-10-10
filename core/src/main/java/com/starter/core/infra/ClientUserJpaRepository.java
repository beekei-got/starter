package com.starter.core.infra;

import com.starter.core.domain.user.client.ClientUser;
import com.starter.core.domain.user.client.ClientUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientUserJpaRepository extends JpaRepository<ClientUser, Long>, ClientUserRepository {
}
