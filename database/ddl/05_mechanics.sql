-- =========================================================
--  MECHANICS
-- =========================================================

CREATE TABLE mechanics (
                           id BIGSERIAL PRIMARY KEY,
                           user_account_id BIGINT NOT NULL,
                           full_name VARCHAR(100) NOT NULL,
                           phone VARCHAR(20) NOT NULL UNIQUE,
                           specialty VARCHAR(60) NOT NULL,
                           availability_status mechanic_status NOT NULL DEFAULT 'AVAILABLE',
                           active BOOLEAN NOT NULL DEFAULT TRUE,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT uq_mechanics_user_account
                               UNIQUE (user_account_id),

                           CONSTRAINT fk_mechanics_user_account
                               FOREIGN KEY (user_account_id)
                                   REFERENCES user_accounts(id)
                                   ON DELETE RESTRICT
);