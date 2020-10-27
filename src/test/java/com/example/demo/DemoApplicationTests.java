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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
//@ContextConfiguration(classes = DemoUsingProcessor.class)
//@Import(DemoApplicationTests.Config.class)
//@ContextConfiguration(classes = DemoApplicationTests.Config.class)
@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
@DirtiesContext
public class DemoApplicationTests {


    @Autowired
    Controller demoUsingProcessor;

    @Autowired
    MessageCollector messageCollector;

    @Test
    public void send() {
        String inputString = "DATA129 testIn";

        Message<String> message = MessageBuilder
                .withPayload(inputString)
                .build();
        demoUsingProcessor.channel.outChannel().send(message);
        Object payload = messageCollector.forChannel(demoUsingProcessor.channel.outChannel()).poll().getPayload();
        System.out.println(payload);
    }

   /* @EnableBinding(Processor.class)
    @EnableAutoConfiguration
    @PropertySource("classpath:application-test.yml")
    public static class Config{

    }*/
}
