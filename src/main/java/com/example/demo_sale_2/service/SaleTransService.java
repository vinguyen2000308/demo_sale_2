package com.example.demo_sale_2.service;

import com.example.demo_sale_2.domain.CustomerDebit;
import com.example.demo_sale_2.domain.SaleTran;
import com.example.demo_sale_2.domain.command.MakeSaleTranCommand;
import com.example.demo_sale_2.domain.reply.CreateSaleTransReply;
import com.example.demo_sale_2.repo.CustomerDebitRepo;
import com.example.demo_sale_2.repo.SaleTranRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class SaleTransService {

    @Autowired
    private SaleTranRepo saleTranRepo;

    @Autowired
    private CustomerDebitRepo customerDebitRepo;

    public CreateSaleTransReply createSaleTransReply(MakeSaleTranCommand makeSaleTranCommand) {
        System.out.println(makeSaleTranCommand);
        Double totalPriceWithTax;
        Double totalPrice = makeSaleTranCommand.getOrderItemList().stream()
                .map(item -> item.getPrice() * item.getTotal())
                .reduce(0.0, (a, b) -> (a + b));
        if (Objects.nonNull(makeSaleTranCommand.getTax())) {
            totalPriceWithTax = totalPrice - (Double.valueOf(makeSaleTranCommand.getTax()) / Double.valueOf(100)) * totalPrice;
        } else
            totalPriceWithTax = totalPrice;

        Optional<CustomerDebit> customerDebit = customerDebitRepo.findById(makeSaleTranCommand.getCustomerId());
        if (customerDebit.isPresent())
            return CreateSaleTransReply.builder()
                    .code("400")
                    .message("Not found customer debit with customer id " + makeSaleTranCommand.getCustomerId())
                    .build();

        if (totalPriceWithTax > customerDebit.get().getDebitValue())
            return CreateSaleTransReply.builder()
                    .code("400")
                    .message("Can not pay for " + totalPriceWithTax + " debit value " + customerDebit.get()
                            .getDebitValue())
                    .build();

        SaleTran saleTran = SaleTran.builder()
                .customerId(makeSaleTranCommand.getCustomerId())
                .orderId(makeSaleTranCommand.getOrderId())
                .totalPrice(totalPrice)
                .totalPriceTax(totalPriceWithTax)
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();
        SaleTran saveSaleTrans = saleTranRepo.save(saleTran);

        CreateSaleTransReply createSaleTransReply = new CreateSaleTransReply();
        if (Objects.nonNull(saveSaleTrans)) {
            createSaleTransReply.setCode("200");
            createSaleTransReply.setMessage("SALE TRANS CREATED");
            createSaleTransReply.setSaleTransId(saveSaleTrans.getId());
        } else {
            createSaleTransReply.setCode("400");
            createSaleTransReply.setMessage("CAN NOT CREATE SALE TRANS");
        }
        return createSaleTransReply;
    }
}
