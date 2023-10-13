package io.github.zemise.security01.demo.onetoone_bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bi_customer")
@Data
public class BiCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "cust_address")
    private String address;

    /**
     * mappedBy 将外键约束执行另一方维护（通常在双向关联关系中，放弃一方的外键约束）
     *  值 = 另一方关联属性名
     */
    @OneToOne(mappedBy = "customer",
            cascade = CascadeType.PERSIST)
    // 设置外键的字段的ID
    @JoinColumn(name = "account_id")
    private BiAccount account;
}
