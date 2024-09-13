package com.starter.user.domain.user.client;

import java.util.Optional;

public interface ClientUserRepository {
    ClientUser saveAndFlush(ClientUser clientUser);
    long countByEmail(String email);
    long countByEmailAndIdNot(String email, long id);

    Optional<ClientUser> findByEmail(String email);
}
