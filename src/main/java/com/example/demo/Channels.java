package com.example.demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

public interface Channels {

    @Output("testout")
    MessageChannel outChannel();

    @Output("testin")
    MessageChannel inChannel();

    @Output("checktopic")
    MessageChannel checktopic();

    @Input("bpsIn")
    MessageChannel bpsInChannel();

    @Output("bpsOut")
    MessageChannel bpsOutChannel();
}
