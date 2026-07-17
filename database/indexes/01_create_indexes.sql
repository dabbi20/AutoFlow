-- =========================================================
-- FOREIGN KEYS
-- =========================================================

CREATE INDEX idx_vehicles_customer_id
    ON vehicles (customer_id);

CREATE INDEX idx_service_orders_customer_id
    ON service_orders (customer_id);

CREATE INDEX idx_service_orders_vehicle_id
    ON service_orders (vehicle_id);

CREATE INDEX idx_service_orders_mechanic_id
    ON service_orders (mechanic_id);

CREATE INDEX idx_service_orders_advisor_id
    ON service_orders (advisor_id);

CREATE INDEX idx_diagnoses_service_order_id
    ON diagnoses (service_order_id);

CREATE INDEX idx_service_order_services_service_id
    ON service_order_services (service_id);

CREATE INDEX idx_payments_invoice_id
    ON payments (invoice_id);

CREATE INDEX idx_payments_received_by
    ON payments (received_by);

CREATE INDEX idx_user_account_roles_role_id
    ON user_account_roles (role_id);


-- =========================================================
-- COMMON FILTERS
-- =========================================================

CREATE INDEX idx_service_orders_status
    ON service_orders (status);

CREATE INDEX idx_service_orders_priority
    ON service_orders (priority);

CREATE INDEX idx_service_orders_created_at
    ON service_orders (created_at);

CREATE INDEX idx_invoices_status
    ON invoices (status);

CREATE INDEX idx_invoices_due_date
    ON invoices (due_date);

CREATE INDEX idx_payments_status
    ON payments (status);

CREATE INDEX idx_payments_paid_at
    ON payments (paid_at);


-- =========================================================
-- BUSINESS-SPECIFIC INDEXES
-- =========================================================

CREATE UNIQUE INDEX uq_diagnoses_one_final_per_order
    ON diagnoses (service_order_id)
    WHERE is_final = TRUE;

CREATE INDEX idx_active_services_name
    ON services (name)
    WHERE active = TRUE;

CREATE INDEX idx_available_mechanics
    ON mechanics (availability_status)
    WHERE active = TRUE;