package com.example.demo_order_2.service;

import com.example.demo_order_2.domain.SaleTran;
import com.example.demo_order_2.domain.command.MakeSaleTranCommand;
import com.example.demo_order_2.domain.reply.CreateSaleTransReply;
import com.example.demo_order_2.domain.reply.ReplyMessage;
import com.example.demo_order_2.repo.SaleTranRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SaleTransService {

    @Autowired
    private SaleTranRepo saleTranRepo;

    public CreateSaleTransReply createSaleTransReply(MakeSaleTranCommand makeSaleTranCommand) {
        System.out.println(makeSaleTranCommand);
        Double totalPrice = makeSaleTranCommand.getOrderItemList().stream()
                .map(item -> item.getPrice() * item.getTotal())
                .reduce(0.0, (a, b) -> (a + b));
        CreateSaleTransReply createSaleTransReply = new CreateSaleTransReply();
        SaleTran saleTran = SaleTran.builder()
                .customerId(makeSaleTranCommand.getCustomerId())
                .orderId(makeSaleTranCommand.getOrderId())
                .totalPrice(totalPrice)
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();
        SaleTran saveSaleTrans = saleTranRepo.save(saleTran);
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
