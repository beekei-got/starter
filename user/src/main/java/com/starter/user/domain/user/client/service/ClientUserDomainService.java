package com.starter.user.domain.user.client.service;

import com.starter.user.domain.user.client.ClientUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientUserDomainService {

    private final ClientUserRepository clientUserRepository;

}
