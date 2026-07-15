-- =========================================================
--  PAYMENTS
-- =========================================================

CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,
                          invoice_id BIGINT NOT NULL,
                          method payment_method NOT NULL,
                          status payment_status NOT NULL DEFAULT 'PENDING',
                          amount DECIMAL(12,2) NOT NULL,
                          reference_number VARCHAR(50),
                          received_by BIGINT NOT NULL,
                          paid_at TIMESTAMP,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_payments_invoice
                              FOREIGN KEY (invoice_id)
                                  REFERENCES invoices(id)
                                  ON DELETE RESTRICT,

                          CONSTRAINT fk_payments_received_by
                              FOREIGN KEY (received_by)
                                  REFERENCES service_advisors(id)
                                  ON DELETE RESTRICT,

                          CONSTRAINT chk_payments_amount
                              CHECK (amount > 0)
);