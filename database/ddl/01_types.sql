-- =========================================================
--  ENUM TYPES
-- =========================================================

CREATE TYPE order_status AS ENUM (
    'RECEIVED',
    'IN_DIAGNOSIS',
    'WAITING_APPROVAL',
    'APPROVED',
    'REJECTED',
    'IN_REPAIR',
    'READY',
    'INVOICED',
    'PAID',
    'DELIVERED',
    'NOT_VIABLE'
);

CREATE TYPE priority_type AS ENUM (
    'LOW',
    'MEDIUM',
    'HIGH',
    'URGENT'
);

CREATE TYPE mechanic_status AS ENUM (
    'AVAILABLE',
    'BUSY',
    'VACATION',
    'INACTIVE'
);

CREATE TYPE vehicle_type AS ENUM (
    'CAR',
    'MOTORCYCLE',
    'TRUCK',
    'BUS',
    'VAN',
    'MACHINERY',
    'OTHER'
);

CREATE TYPE invoice_status AS ENUM (
    'PENDING',
    'PAID',
    'CANCELLED',
    'OVERDUE'
);

CREATE TYPE payment_method AS ENUM (
    'CASH',
    'CREDIT_CARD',
    'DEBIT_CARD',
    'BANK_TRANSFER',
    'MOBILE_PAYMENT'
);

CREATE TYPE payment_status AS ENUM (
    'PENDING',
    'PROCESSING',
    'COMPLETED',
    'FAILED',
    'REFUNDED'
);

CREATE TYPE billing_type AS ENUM (
    'PERSONAL',
    'BUSINESS'
);
