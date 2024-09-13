package com.starter.user.infra;

import com.starter.user.domain.user.client.ClientUser;
import com.starter.user.domain.user.client.ClientUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientUserJpaRepository extends JpaRepository<ClientUser, Long>, ClientUserRepository {
}
