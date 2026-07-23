package com.autoflow.Repository;

import com.autoflow.Entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
}
