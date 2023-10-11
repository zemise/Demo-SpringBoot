package io.github.zemise.security01.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "sys_permission")
@Data
public class SysPermission implements Serializable {
    private static final long serialValueUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20)
    private Long id;

    @Column(length = 20)
    private Long pid;

    private String name;

    private String url;

    private String description;
}
