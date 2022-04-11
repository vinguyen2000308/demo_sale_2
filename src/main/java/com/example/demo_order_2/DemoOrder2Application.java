package com.example.demo_order_2;

import com.example.demo_order_2.domain.Command;
import com.example.demo_order_2.domain.Data;
import com.example.demo_order_2.domain.command.MakeSaleTranCommand;
import com.example.demo_order_2.domain.reply.CreateSaleTransReply;
import com.example.demo_order_2.handler.SaleTransHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Objects;

@SpringBootApplication
public class DemoOrder2Application {




    public static void main(String[] args) {
        SpringApplication.run(DemoOrder2Application.class, args);
    }




}
