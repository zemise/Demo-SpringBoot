package io.github.zemise.security01.jpa.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sys_role")
@Data
public class SysRole implements Serializable {
    private static final long serialValueUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20)
    private Long id;

    private String name;

    public SysRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SysRole() {
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sys_role_permission",
            joinColumns = {@JoinColumn(name = "sys_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_permission_id")})
    private List<SysPermission> permissions;

    private @Version Long version;
}
