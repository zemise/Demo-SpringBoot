package io.github.zemise.security01.onetoone;

import io.github.zemise.security01.domain.onetomany.CustomerM;
import io.github.zemise.security01.domain.onetomany.Message;
import io.github.zemise.security01.domain.onetomany.MessageRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class ManyToOneTest {
    @Resource
    MessageRepository repository;

    @Test
    // 插入
    void test(){
        // 一
        CustomerM customer = new CustomerM();
        customer.setName("李梅");

        // 多
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("您好", customer));
        messages.add(new Message("在吗", customer));

        repository.saveAll(messages);
    }

    // 多对一：根据客户id查询对应的所有信息
    // 通过“一”进行查询，在一对多中实现是更合理的
    @Test
    @Transactional(readOnly = true)
    void testFind(){
        CustomerM customerM = new CustomerM();
        customerM.setId(1L);
        // 只能通过ID查询，下方的名字设置，其实并没有影响
        customerM.setName("xxxx");

        List<Message> messages = repository.findByCustomer(customerM);
        // 这里隐式调用ToString方法
        System.out.println(messages);

        // 如果 @ManyToOne(cascade = CascadeType.PERSIST)设置如此，就只会删除message那张表内容
        repository.deleteAll(messages);
    }

    // 删除
    @Test
    void testDelete(){

    }
}
