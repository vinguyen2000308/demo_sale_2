package com.example.demo_sale_2.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "sale")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SaleTran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_trans_id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_price_tax")
    private Double totalPriceTax;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "created_date")
    private LocalDateTime createdDateTime;

    @Column(name = "updated_date")
    private LocalDateTime updatedDateTime;


}
