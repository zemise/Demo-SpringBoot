package io.github.zemise.security01.domain.manytomany;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "a_employee")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 单向 多对多
    /**
     * 中间表需要通过@JoinTable来维护 （不设置也会自动生成）
     * name 指定中间表的名称
     * JoinColums 设置本表的外键名称
     * inverseJoinColumns 设置关联表的外键名称
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "a_employee_role_relation",
            joinColumns = {@JoinColumn(name = "em_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id")})
    private List<Role> roles;

    // 乐观锁
    private @Version Long version;

    // 审计相关
    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String modifiedBy;

    /**
     * 实体创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date dateCreated = new Date();

    /**
     * 实体修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date dateModified = new Date();
}
