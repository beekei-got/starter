package com.starter.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.starter.app", "com.starter.core"})
@SpringBootTest
class AppApplicationTests {

    @Test
    void contextLoads() {
    }

}
