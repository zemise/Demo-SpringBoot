package io.github.zemise.security01.domain.onetoone_unidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "uni_account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tb_account")
    private String account;

    @Column(name = "tb_password")
    private String password;


}
