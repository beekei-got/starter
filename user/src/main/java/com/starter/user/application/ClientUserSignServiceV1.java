package com.starter.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ClientUserSignServiceV1 {
    @Transactional
    void resignClientUser(long userId);
}
