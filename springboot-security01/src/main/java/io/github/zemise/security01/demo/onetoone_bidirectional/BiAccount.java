package io.github.zemise.security01.demo.onetoone_bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bi_account")
@Data
public class BiAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tb_account")
    private String account;

    @Column(name = "tb_password")
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private BiCustomer customer;


}
