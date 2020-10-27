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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoUsingBinderChannelResolver.class)
@ContextConfiguration(classes = DemoUsingBinderChannelResolver.class)
@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
@DirtiesContext
public class BinderChannelTests {


/*    @Autowired
    DemoUsingBinderChannelResolver binderChannelResolver;*/
    @Autowired
    BinderAwareChannelResolver resolver;

    @Autowired
    MessageCollector collector;
    @Test
    public void send() {
        String inputString = "testIn";

        Message<String> message = MessageBuilder
                .withPayload(inputString)
                .build();
        resolver.resolveDestination("output").send(message);

        Object payload = collector.forChannel(resolver.resolveDestination("output")).poll().getPayload();
        System.out.println(payload);
    }


}
