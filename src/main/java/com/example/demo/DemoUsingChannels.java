package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class DemoUsingChannels implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoUsingChannels.class, args);
    }

    @Autowired
    Channels processor;

//    @SendTo(value = "checktopic")
    public String publishMessage(String text) {
        Message<String> message = MessageBuilder
                .withPayload(text)
                .build();
        System.out.println("Message Sent-"+message);
        processor.checktopic().send(message);
        return "Message Sent Successfully Using Processor-- " + message;
    }

    @StreamListener(value = "checktopic")
    public void receiveMsg(String text) {
        Message<String> message = MessageBuilder
                .withPayload(text)
                .build();
        System.out.println("Message received-"+message);

    }
    @Override
    public void run(String... args) throws Exception {
        String msg =publishMessage("Good Morning");
        System.out.println("OK-"+msg);
    }
}
