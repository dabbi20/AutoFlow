-- =========================================================
--  INVOICES
-- =========================================================

CREATE TABLE invoices (
                          id BIGSERIAL PRIMARY KEY,
                          invoice_number VARCHAR(60) NOT NULL UNIQUE,
                          service_order_id BIGINT NOT NULL,
                          billing_name VARCHAR(90) NOT NULL,
                          billing_document VARCHAR(20) NOT NULL,
                          billing_type billing_type NOT NULL,
                          description TEXT NOT NULL,
                          subtotal DECIMAL(12,2) NOT NULL,
                          tax DECIMAL(12,2) NOT NULL,
                          total DECIMAL(12,2) NOT NULL,
                          status invoice_status NOT NULL DEFAULT 'PENDING',
                          invoice_date DATE NOT NULL,
                          due_date DATE NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT uq_invoices_service_order
                              UNIQUE (service_order_id),

                          CONSTRAINT fk_invoices_service_order
                              FOREIGN KEY (service_order_id)
                                  REFERENCES service_orders(id)
                                  ON DELETE RESTRICT,

                          CONSTRAINT chk_invoices_subtotal
                              CHECK (subtotal >= 0),

                          CONSTRAINT chk_invoices_tax
                              CHECK (tax >= 0),

                          CONSTRAINT chk_invoices_total
                              CHECK (total >= 0),

                          CONSTRAINT chk_invoices_due_date
                              CHECK (due_date >= invoice_date),

                          CONSTRAINT chk_invoices_total_calculation
                              CHECK (total = subtotal + tax)
);