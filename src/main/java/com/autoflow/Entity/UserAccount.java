package com.autoflow.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue  (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", nullable = false,unique = true,length = 150)
    private String email;
    @Column(name = "password_hash",nullable = false,length = 255)
    private String  passwordHash;
    @Column(name = "active",nullable = false)
    private Boolean active = true;
    @Column(name =  "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userAccount")
    private Set<UserAccountRole> userAccountRoles = new HashSet<>();

}
