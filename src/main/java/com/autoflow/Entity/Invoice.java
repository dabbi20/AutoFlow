package com.autoflow.Entity;

import com.autoflow.Enums.BillingType;
import com.autoflow.Enums.InvoiceStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;
    @NotBlank
    @Size(max = 60)
    @Column(name = "invoice_number", nullable = false, unique = true, length = 60)
private String invoiceNumber;
    @NotBlank
    @Size(max = 90)
    @Column(name = "billing_name", nullable = false, length = 90)
private String billingName;
    @NotBlank
    @Size(max = 20)
    @Column(name = "billing_document", nullable = false, length = 20)
private String billingDocument;
    @NotNull
@Enumerated(EnumType.STRING)
@Column(name = "billing_type",nullable = false)
private BillingType billingType;
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull
    @PositiveOrZero
    @Column(
            name = "subtotal",
            nullable = false,
            precision = 12,
            scale = 2
    )
private BigDecimal subtotal;
    @NotNull
    @PositiveOrZero
@Column(name = "tax",nullable = false,  precision = 12,
        scale = 2)
private BigDecimal tax;
    @NotNull
    @PositiveOrZero
@Column(name = "total",nullable = false,  precision = 12,
        scale = 2)
private BigDecimal total;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InvoiceStatus invoiceStatus = InvoiceStatus.PENDING;
@NotNull
@Column(name = "invoice_date", nullable = false)
private LocalDate invoiceDate;
@NotNull
@Column(name = "due_date", nullable = false)
private LocalDate dueDate;
@Column(name = "created_at",nullable = false,updatable = false)
private LocalDateTime createdAt;
@Column(name = "updated_at",nullable = false)
private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();

        if (this.createdAt == null) {
            this.createdAt = now;
        }

        if (this.updatedAt == null) {
            this.updatedAt = now;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    @NotNull
@OneToOne
@JoinColumn(name = "service_order_id",nullable = false,unique = true)
private ServiceOrder serviceOrder;

    @OneToMany(mappedBy = "invoice")
    private Set<Payment> payments = new HashSet<>();


    @Override
    public boolean equals(Object obj) {
        if (this == obj){return  true;}
        if (!(obj instanceof Invoice invoice)){
            return false;
        }
        return invoiceNumber != null && invoiceNumber.equals(invoice.invoiceNumber);
    }

    @Override
    public int hashCode() {
        return invoiceNumber != null ? invoiceNumber.hashCode() : 0;
    }

    public Invoice(){}

    public Invoice(Long id, String invoiceNumber, String billingName, String billingDocument, BillingType  billingType, String description, BigDecimal subtotal, BigDecimal tax, BigDecimal total, InvoiceStatus  invoiceStatus , LocalDate invoiceDate, LocalDate dueDate, ServiceOrder serviceOrder) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.billingName = billingName;
        this.billingDocument = billingDocument;
        this.billingType = billingType;
        this.description = description;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        this.invoiceStatus = invoiceStatus != null
                ? invoiceStatus
                : InvoiceStatus.PENDING;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.serviceOrder = serviceOrder;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingDocument() {
        return billingDocument;
    }

    public void setBillingDocument(String billingDocument) {
        this.billingDocument = billingDocument;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public BillingType getBillingType() {
        return billingType;
    }

    public void setBillingType(BillingType billingType) {
        this.billingType = billingType;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    //METODOS AUXILIARES

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setInvoice(this);
    }

    public void removePayment(Payment payment) {
        payments.remove(payment);
        payment.setInvoice(null);
    }
}



