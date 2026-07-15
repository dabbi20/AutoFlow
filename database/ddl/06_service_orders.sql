-- =========================================================
--  SERVICE ORDERS
-- =========================================================

CREATE TABLE service_orders (
                                id BIGSERIAL PRIMARY KEY,
                                order_number VARCHAR(30) NOT NULL UNIQUE,
                                reason_for_visit TEXT NOT NULL,
                                status order_status NOT NULL DEFAULT 'RECEIVED',
                                priority priority_type NOT NULL DEFAULT 'MEDIUM',
                                customer_id BIGINT NOT NULL,
                                vehicle_id BIGINT NOT NULL,
                                mechanic_id BIGINT NOT NULL,
                                advisor_id BIGINT NOT NULL,
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                closed_at TIMESTAMP,

                                CONSTRAINT fk_service_orders_customer
                                    FOREIGN KEY (customer_id)
                                        REFERENCES customers(id)
                                        ON DELETE RESTRICT,

                                CONSTRAINT fk_service_orders_vehicle
                                    FOREIGN KEY (vehicle_id)
                                        REFERENCES vehicles(id)
                                        ON DELETE RESTRICT,

                                CONSTRAINT fk_service_orders_mechanic
                                    FOREIGN KEY (mechanic_id)
                                        REFERENCES mechanics(id)
                                        ON DELETE RESTRICT,

                                CONSTRAINT fk_service_orders_advisor
                                    FOREIGN KEY (advisor_id)
                                        REFERENCES service_advisors(id)
                                        ON DELETE RESTRICT,

                                CONSTRAINT chk_service_orders_closed_at
                                    CHECK (
                                        closed_at IS NULL
                                            OR closed_at >= created_at
                                        )
);
