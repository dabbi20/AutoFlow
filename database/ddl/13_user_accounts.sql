-- =========================================================
--  USER ACCOUNTS
-- =========================================================

CREATE TABLE user_accounts (
                               id BIGSERIAL PRIMARY KEY,
                               email VARCHAR(150) NOT NULL UNIQUE,
                               password_hash VARCHAR(255) NOT NULL,
                               active BOOLEAN NOT NULL DEFAULT TRUE,
                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);