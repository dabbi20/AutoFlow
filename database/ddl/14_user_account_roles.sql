-- =========================================================
--  USER ACCOUNT ROLES
-- =========================================================

CREATE TABLE user_account_roles (
                                    user_account_id BIGINT NOT NULL,
                                    role_id BIGINT NOT NULL,

                                    CONSTRAINT pk_user_account_roles
                                        PRIMARY KEY (user_account_id, role_id),

                                    CONSTRAINT fk_user_account_roles_user_account
                                        FOREIGN KEY (user_account_id)
                                            REFERENCES user_accounts(id)
                                            ON DELETE CASCADE,

                                    CONSTRAINT fk_user_account_roles_role
                                        FOREIGN KEY (role_id)
                                            REFERENCES roles(id)
                                            ON DELETE RESTRICT
);
