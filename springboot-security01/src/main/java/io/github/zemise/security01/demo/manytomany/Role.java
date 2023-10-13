package io.github.zemise.security01.demo.manytomany;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "a_role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;

}
