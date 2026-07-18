package com.autoflow.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column (name = "name",nullable = false,unique = true,length = 50)
    private String name;
    @Column(name = "description",length = 200)
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<UserAccountRole>userAccountRoles = new HashSet<>();
}
