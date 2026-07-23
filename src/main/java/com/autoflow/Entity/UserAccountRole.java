package com.autoflow.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_account_roles")
public class UserAccountRole {
    @EmbeddedId
    private UserAccountRoleId id;

    @NotNull
    @ManyToOne
    @MapsId("userAccountId")
    @JoinColumn(name = "user_account_id",nullable = false)
    private UserAccount userAccount;

    @NotNull
    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    public  UserAccountRole(){}

    public UserAccountRole(UserAccountRoleId id, UserAccount userAccount, Role role) {
        this.id = id;
        this.userAccount = userAccount;
        this.role = role;
    }

    public UserAccountRoleId getId() {
        return id;
    }

    public void setId(UserAccountRoleId id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
