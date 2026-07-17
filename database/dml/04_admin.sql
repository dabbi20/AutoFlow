-- =========================================
-- ADMINISTRADOR INICIAL
-- =========================================

INSERT INTO user_accounts (
    email,
    password_hash
)
VALUES (
           'admin@autoflow.com',
           'ABC123'
       )
RETURNING
    id,
    email;



INSERT INTO user_account_roles (
    user_account_id,
    role_id
)
VALUES (
           6,
           1
       )
RETURNING
    user_account_id,
    role_id;