package io.github.zemise.security01.demo.onetomany;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "m_customer")
@Data
public class CustomerM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;


    // 一对多
    // 一对多，默认把@OneToMany放在一的这一类，但其外键字段实际在生成在多的那张表
    /**
     * OneToMany fetch默认懒加载
     */
//    @OneToMany(cascade = CascadeType.PERSIST)
    @OneToMany(cascade = CascadeType.ALL)
    // 设置外键的字段的ID
    @JoinColumn(name = "customer_id")
    private List<Message> messages;

}
