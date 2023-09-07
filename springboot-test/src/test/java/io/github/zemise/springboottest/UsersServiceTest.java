package io.github.zemise.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * UsersService测试类
 */
@SpringBootTest
public class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Test
    public void testAdd(){
        usersService.add();
    }
}
