package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableBinding(Channels.class)
public class DemoUsingProcessorInOut {

    public static void main(String[] args) {
        SpringApplication.run(DemoUsingProcessorInOut.class, args);
    }

    @Autowired
    Channels channels;

    @StreamListener(value = "testin")
    @SendTo(value = "testout")
    public String publishMessage(String text) {
        Message<String> message = MessageBuilder
                .withPayload(text)
                .build();
        //processor.output().send(message);
        return "Message Sent Successfully Using Processor-- " + message;
    }


}
