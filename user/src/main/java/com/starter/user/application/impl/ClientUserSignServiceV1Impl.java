package com.starter.user.application.impl;

import com.starter.user.application.ClientUserSignServiceV1;
import com.starter.user.domain.user.client.ClientUser;
import com.starter.user.domain.user.client.service.ClientUserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientUserSignServiceV1Impl implements ClientUserSignServiceV1 {

    private final ClientUserDomainService clientUserDomainService;

    @Override
    public void resignClientUser(long userId) {
        ClientUser clientUser = clientUserDomainService.getClientUser(userId);
        clientUser.resign();
    }

}
