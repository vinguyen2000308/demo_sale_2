package com.example.demo_sale_2.domain.command;

import com.example.demo_sale_2.domain.Command;
import com.example.demo_sale_2.domain.dto.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class MakeSaleTranCommand implements Command {

    private Long customerId;
    private Long orderId;
    private List<OrderItem> orderItemList;
    private Double tax;

}
