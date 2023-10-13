package io.github.zemise.security01.demo.onetomany;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    // 根据客户ID查询所有信息
    // 通过规定方法名来实现关联查询：需要通过关联属性来进行匹配
    // 但是只能通过id进行匹配
    List<Message> findByCustomer(CustomerM customerM);

}
