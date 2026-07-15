-- =========================================================
--  VEHICLES
-- =========================================================

CREATE TABLE vehicles (
                          id BIGSERIAL PRIMARY KEY,
                          plate VARCHAR(20) NOT NULL UNIQUE,
                          vehicle_type vehicle_type NOT NULL,
                          brand VARCHAR(50) NOT NULL,
                          model VARCHAR(50) NOT NULL,
                          model_year INT NOT NULL,
                          color VARCHAR(80) NOT NULL,
                          chassis_number VARCHAR(50) NOT NULL UNIQUE,
                          engine_number VARCHAR(50) NOT NULL UNIQUE,
                          mileage INT NOT NULL,
                          customer_id BIGINT NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_vehicles_customer
                              FOREIGN KEY (customer_id)
                                  REFERENCES customers(id)
                                  ON DELETE RESTRICT,

                          CONSTRAINT chk_vehicles_mileage
                              CHECK (mileage >= 0),

                          CONSTRAINT chk_vehicles_model_year
                              CHECK (model_year >= 1886)
);
