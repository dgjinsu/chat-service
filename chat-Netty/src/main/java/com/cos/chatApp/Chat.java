package com.cos.chatApp;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "chat")
public class Chat {
    @Id
    private String id;
    private String msg;
    private String sender;
    private String receiver; // 1:1 채팅에서 필요
    private Integer roomNum; // 방 번호
    private LocalDateTime createdAt;
}
