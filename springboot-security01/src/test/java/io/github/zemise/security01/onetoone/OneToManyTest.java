package io.github.zemise.security01.onetoone;

import io.github.zemise.security01.domain.onetomany.CustomerM;
import io.github.zemise.security01.domain.onetomany.CustomerMRepository;
import io.github.zemise.security01.domain.onetomany.Message;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class OneToManyTest {
    @Resource
    CustomerMRepository repository;

    @Test
    // 插入
    void test(){
        ArrayList<Message> messageList = new ArrayList<>();
        messageList.add(new Message("hello001"));
        messageList.add(new Message("hello002"));
        messageList.add(new Message("hello003"));

        CustomerM customerM = new CustomerM();
        customerM.setName("赵大");
        customerM.setAddress("北京");
        customerM.setMessages(messageList);

        repository.save(customerM);
    }

    // 查询
    @Test
    @Transactional(readOnly = true)
    void testFind(){
        Optional<CustomerM> byId = repository.findById(3L);
        System.out.println(byId);
    }

    // 删除
    @Test
    void testDelete(){
        repository.deleteById(4L);
    }
}
