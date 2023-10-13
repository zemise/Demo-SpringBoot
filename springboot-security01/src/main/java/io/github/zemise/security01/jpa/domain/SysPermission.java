package io.github.zemise.security01.jpa.domain;

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

    public SysPermission(Long pid, String name, String url, String description) {
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public SysPermission() {
    }

    private String url;

    private String description;

    private @Version Long version;
}
