package com.autoflow.Repository;

import com.autoflow.Entity.UserAccountRole;
import com.autoflow.Entity.UserAccountRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRoleRepository extends JpaRepository<UserAccountRole, UserAccountRoleId> {
}
