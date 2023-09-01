package com.cos.chatApp;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {

    // Flux (흐름)
    // 데이터를 흘려서 계속 받겠다.
    // Response 를 유지하면서 데이터를 계속 보낼 수 있다.
    @Tailable //커서를 닫지 않고 계속 유지
    @Query("{ sender : ?0, receiver : ?1}")
    Flux<Chat> mFindBySender(String sender, String receiver);
}
