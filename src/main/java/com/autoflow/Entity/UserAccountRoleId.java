package com.autoflow.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserAccountRoleId implements Serializable {

    @Column(name = "user_account_id")
    private Long userAccountId;

    @Column(name = "role_id")
    private Long roleId;

    public UserAccountRoleId() {
    }

    public UserAccountRoleId(Long userAccountId, Long roleId) {
        this.userAccountId = userAccountId;
        this.roleId = roleId;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        UserAccountRoleId that = (UserAccountRoleId) object;

        return Objects.equals(userAccountId, that.userAccountId)
                && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountId, roleId);
    }
}