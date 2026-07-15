
-- =========================================================
--  SERVICES
-- =========================================================

CREATE TABLE services (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL UNIQUE,
                          description TEXT NOT NULL,
                          base_price DECIMAL(12,2) NOT NULL,
                          estimated_hours INT NOT NULL,
                          active BOOLEAN NOT NULL DEFAULT TRUE,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT chk_services_estimated_hours
                              CHECK (estimated_hours >= 0),

                          CONSTRAINT chk_services_base_price
                              CHECK (base_price >= 0)
);
