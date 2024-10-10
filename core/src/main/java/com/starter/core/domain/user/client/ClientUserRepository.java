package com.starter.core.domain.user.client;

import java.util.Optional;

public interface ClientUserRepository {
    ClientUser saveAndFlush(ClientUser clientUser);
    Optional<ClientUser> findById(long userId);
    Optional<ClientUser> findByEmail(String email);
}
