package com.example.demo_sale_2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "customer_debit")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerDebit {
    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "status")
    private Long status;

    @Column(name = "debit_value")
    private Double debitValue;

    @Column(name = "created_date")
    private LocalDateTime createdDateTime;

    @Column(name = "updated_date")
    private LocalDateTime updatedDateTime;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "updated_user")
    private String updatedUser;


}
