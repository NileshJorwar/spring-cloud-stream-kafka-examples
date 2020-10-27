package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoUsingProcessorInOut.class)
@RunWith(SpringRunner.class)
@DirtiesContext
public class DemoUsingProcessorInOutTests {


    @Autowired
    DemoUsingProcessorInOut demoUsingProcessor;

    @Autowired
    MessageCollector messageCollector;


    @Test
    public void send() {
        String inputString = "testIn";
        Message<String> message = MessageBuilder
                .withPayload(inputString)
                .build();
        demoUsingProcessor.channels.inChannel().send(message);

        Object payload = messageCollector.forChannel(demoUsingProcessor.channels.outChannel()).poll().getPayload();
        System.out.println(payload);



    }


}
