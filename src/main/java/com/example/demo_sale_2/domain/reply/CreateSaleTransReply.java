package com.example.demo_sale_2.domain.reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateSaleTransReply implements ReplyMessage {

    private String code;
    private String message;
    private Long saleTransId;

}
