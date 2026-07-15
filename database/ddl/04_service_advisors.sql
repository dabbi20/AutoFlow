
-- =========================================================
--  SERVICE ADVISORS
-- =========================================================

CREATE TABLE service_advisors (
                                  id BIGSERIAL PRIMARY KEY,
                                  user_account_id BIGINT NOT NULL,
                                  full_name VARCHAR(100) NOT NULL,
                                  phone VARCHAR(20) NOT NULL UNIQUE,
                                  active BOOLEAN NOT NULL DEFAULT TRUE,
                                  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                  CONSTRAINT uq_service_advisors_user_account
                                      UNIQUE (user_account_id),

                                  CONSTRAINT fk_service_advisors_user_account
                                      FOREIGN KEY (user_account_id)
                                          REFERENCES user_accounts(id)
                                          ON DELETE RESTRICT
);
