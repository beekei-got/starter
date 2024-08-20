package com.starter.core.domain.user;

import java.util.Optional;

public interface ClientUserRepository {
    ClientUser saveAndFlush(ClientUser clientUser);
    Optional<ClientUser> findByEmail(String email);
}
