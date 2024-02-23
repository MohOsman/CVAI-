package se.mohosman.cvai.service;

import org.codehaus.groovy.transform.sc.ListOfExpressionsExpression;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CVServiceImpl implements CVService{
 private final ChatClient chatClient;

    public CVServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void uploadCV(File cv) {
    }

    @Override
    public String testChatclient() {
        var prompt = "tell me a joke";
        var aiResponse = this.chatClient.call(new Prompt(prompt));
        return aiResponse.getResult().getOutput().getContent();
    }
}
