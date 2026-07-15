-- =========================================================
--  SERVICE ORDER SERVICES
-- =========================================================

CREATE TABLE service_order_services (
                                        service_order_id BIGINT NOT NULL,
                                        service_id BIGINT NOT NULL,
                                        agreed_price DECIMAL(12,2) NOT NULL,
                                        quantity INT NOT NULL,
                                        notes TEXT NOT NULL,

                                        CONSTRAINT pk_service_order_services
                                            PRIMARY KEY (service_order_id, service_id),

                                        CONSTRAINT fk_service_order_services_order
                                            FOREIGN KEY (service_order_id)
                                                REFERENCES service_orders(id)
                                                ON DELETE RESTRICT,

                                        CONSTRAINT fk_service_order_services_service
                                            FOREIGN KEY (service_id)
                                                REFERENCES services(id)
                                                ON DELETE RESTRICT,

                                        CONSTRAINT chk_service_order_services_quantity
                                            CHECK (quantity > 0),

                                        CONSTRAINT chk_service_order_services_agreed_price
                                            CHECK (agreed_price >= 0)
);


