package com.example.tutorialclient.component;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TutorialClientChannel {

    @Output
    MessageChannel output();

}
