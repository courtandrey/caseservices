package com.github.courtandrey.caseview;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@Configuration
public class CaseRetriever {
    private final CaseRepository repository;
    private final WebSocketHandler handler;

    @Bean
    Consumer<List<Decision>> retrieveCases() {
        return cases -> {
            repository.saveAll(cases);
            sendCases();
        };
    }

    private void sendCases() {
        if (repository.count() > 0) {
            for (WebSocketSession sessionInList : handler.getSessionList()) {
                try {
                    sessionInList.sendMessage(
                            new TextMessage(repository.findAll().toString())
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
