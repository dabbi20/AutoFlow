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

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();

        if (this.createdAt == null) {
            this.createdAt = now;
        }

        if (this.updatedAt == null) {
            this.updatedAt = now;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "userAccount")
    private Set<UserAccountRole> userAccountRoles = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof UserAccount other)){
            return false;
        }
        return  email != null && email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    public UserAccount() {
    }

    public UserAccount(Long id, String email, String passwordHash, Boolean active, Set<UserAccountRole> userAccountRoles) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.active = active != null ? active : true;

        this.userAccountRoles = userAccountRoles;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() {
        return active;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<UserAccountRole> getUserAccountRoles() {
        return userAccountRoles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUserAccountRoles(Set<UserAccountRole> userAccountRoles) {
        this.userAccountRoles = userAccountRoles;
    }
}
