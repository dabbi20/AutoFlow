-- =========================================================
--  CUSTOMERS
-- =========================================================

CREATE TABLE customers (
                           id BIGSERIAL PRIMARY KEY,
                           user_account_id BIGINT NOT NULL,
                           full_name VARCHAR(150) NOT NULL,
                           phone VARCHAR(30) NOT NULL UNIQUE,
                           document_number VARCHAR(20) NOT NULL UNIQUE,
                           address VARCHAR(90) NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT uq_customers_user_account
                               UNIQUE (user_account_id),

                           CONSTRAINT fk_customers_user_account
                               FOREIGN KEY (user_account_id)
                                   REFERENCES user_accounts(id)
                                   ON DELETE RESTRICT
);