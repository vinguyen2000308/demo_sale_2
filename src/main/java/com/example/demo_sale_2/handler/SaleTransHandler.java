package com.example.demo_sale_2.handler;

import com.example.demo_sale_2.common.KafkaProducer;
import com.example.demo_sale_2.domain.Data;
import com.example.demo_sale_2.domain.command.MakeSaleTranCommand;
import com.example.demo_sale_2.domain.reply.CreateSaleTransReply;
import com.example.demo_sale_2.service.SaleTransService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.example.demo_sale_2.common.MessageUtil.checkCommandType;

@Component
public class SaleTransHandler {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SaleTransService saleTransService;

    @KafkaListener(topics = "sale-service", groupId = "group1")
    public void saleServiceHandler(String message) throws JsonProcessingException {
        System.out.println("Received Message: " + message);
        Data data = objectMapper.readValue(message, Data.class);
        if (Objects.nonNull(data))
            handleMessage(data);
    }


    private void handleMessage(Data data) throws JsonProcessingException {
        String type = data.getHeader().get("command_type");
        if (checkCommandType(MakeSaleTranCommand.class, type)) {
            handleMakeCreateSaleTrans(data);
        }

    }


    private void handleMakeCreateSaleTrans(Data data) throws JsonProcessingException {
        MakeSaleTranCommand makeSaleTranCommand = objectMapper.readValue(data.getPayload(), MakeSaleTranCommand.class);
        CreateSaleTransReply saleTransReply = saleTransService.createSaleTransReply(makeSaleTranCommand);
        kafkaProducer.sendMessage(CreateSaleTransReply.class, data, saleTransReply);
    }


}
