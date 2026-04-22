package com.cp5.aiservice.integration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class OpenAiClient {

    private final ChatClient chatClient;

    public OpenAiClient(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String analyze(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}