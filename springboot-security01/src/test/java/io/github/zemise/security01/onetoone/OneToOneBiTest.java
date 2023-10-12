package io.github.zemise.security01.onetoone;


import io.github.zemise.security01.domain.onetoone_bidirectional.BiAccount;
import io.github.zemise.security01.domain.onetoone_bidirectional.BiCustomer;
import io.github.zemise.security01.domain.onetoone_bidirectional.CustomerBiRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OneToOneBiTest {
    @Resource
    CustomerBiRepository repository;

    @Test
    void test(){
        // 初始化数据
        BiCustomer customer = new BiCustomer();
        customer.setName("张三");

        BiAccount account = new BiAccount();
        account.setAccount("123456");
        account.setPassword("password");
        customer.setAccount(account);

        repository.save(customer);
    }

    @Test
    void testSave(){
        BiCustomer user = repository.findByName("张三");
        System.out.println(user);

        user.setAddress("北京");
        repository.save(user);
    }

}
