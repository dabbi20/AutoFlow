-- =========================================================
--  DIAGNOSES
-- =========================================================

CREATE TABLE diagnoses (
                           id BIGSERIAL PRIMARY KEY,
                           service_order_id BIGINT NOT NULL,
                           problem_description TEXT NOT NULL,
                           technical_notes TEXT NOT NULL,
                           customer_message TEXT NOT NULL,
                           estimated_days INT NOT NULL,
                           estimated_price DECIMAL(12,2) NOT NULL,
                           is_final BOOLEAN NOT NULL DEFAULT FALSE,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT fk_diagnoses_service_order
                               FOREIGN KEY (service_order_id)
                                   REFERENCES service_orders(id)
                                   ON DELETE RESTRICT,

                           CONSTRAINT chk_diagnoses_estimated_days
                               CHECK (estimated_days >= 0),

                           CONSTRAINT chk_diagnoses_estimated_price
                               CHECK (estimated_price >= 0)
);
