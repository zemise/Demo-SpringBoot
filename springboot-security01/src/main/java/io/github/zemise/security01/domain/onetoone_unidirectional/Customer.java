package io.github.zemise.security01.domain.onetoone_unidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "uni_customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "cust_address")
    private String address;

    // 单向关联一对一
    /**
     * cascade 设置关联操作
     *  All, 所有持久化操作
     *  PERSIST 只有插入才会执行关联操作
     *  MERGE 只有修改才会执行关联操作
     *  REMOVE 只有删除才会执行关联操作
     *
     * fetch 设置是否懒加载
     *  EAGER 立即加载（默认）
     *  LAZY 懒加载（直到用到对象才会进行查询，因为不是所有关联对象需要用到）
     *
     * orphanRemoval 关联移除（通常在修改的时候会用到）
     *  一旦关联的数据设置null，或修改为其他的关联数据，如果想删除关数据，就可以设置为true
     *
     *  optional 限制关联的对象不能为null
     *      默认为true，表示可以为null
     */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true, optional = false)
    // 设置外键的字段的ID
    @JoinColumn(name = "account_id")
    private Account account;
}
