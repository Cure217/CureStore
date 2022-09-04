package com.aliano.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "message",topic = "myTopic")
@Slf4j
public class MessageService implements RocketMQListener<String> {

    @Autowired
    private WebSocket webSocket;

    @Override
    public void onMessage(String o) {
        log.info("收到消息:{}", o);
        this.webSocket.sendMessage(o);
    }
}
