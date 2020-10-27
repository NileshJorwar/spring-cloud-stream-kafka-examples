package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoUsingProcessor.class)
@RunWith(SpringRunner.class)
@DirtiesContext
public class DemoUsingProcessorTests {


    @Autowired
    DemoUsingProcessor demoUsingProcessor;

    @Autowired
    MessageCollector messageCollector;


    @Test
    public void send() {
        String inputString = "testIn";

        Message<String> message = MessageBuilder
                .withPayload(inputString)
                .build();
        demoUsingProcessor.processor.output().send(message);

        Object payload = messageCollector.forChannel(demoUsingProcessor.processor.output()).poll().getPayload();
        System.out.println(payload);
    }


}
