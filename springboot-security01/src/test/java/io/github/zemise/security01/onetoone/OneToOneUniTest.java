package io.github.zemise.security01.onetoone;

import io.github.zemise.security01.demo.onetoone_unidirectional.Account;
import io.github.zemise.security01.demo.onetoone_unidirectional.Customer;
import io.github.zemise.security01.demo.onetoone_unidirectional.CustomerRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class OneToOneUniTest {
    @Resource
    CustomerRepository repository;

    @Test
    void test(){
        // 初始化数据
        Customer customer = new Customer();
        customer.setName("张三");

        Account account = new Account();
        account.setAccount("123456");
        account.setPassword("password");
        customer.setAccount(account);

        repository.save(customer);
    }

    @Test
    // 设置了懒加载后需添加@Transactional
    // 为什么懒加载需要配置事务
    // 当通过repository调用完查询方法后，session就会立即关闭，一旦session关闭就不能查询
    // 加了事务后，就能让session直到事务关闭才会关闭
    @Transactional(readOnly = true)
    void testSave(){
        Customer user = repository.findByName("张三"); // 只查询用户，session关闭
        System.out.println(user);

        user.setAddress("北京");
        repository.save(user);
    }

}
