package io.github.zemise.springboottest.test;

import io.github.zemise.springboottest.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
