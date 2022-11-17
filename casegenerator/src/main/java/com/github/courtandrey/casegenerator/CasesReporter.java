package com.github.courtandrey.casegenerator;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@AllArgsConstructor
@Configuration
public class CasesReporter {
    private CaseGenerator generator;

    @Bean
    Supplier<Iterable<Decision>> reportCases() {
        return () -> generator.generate();
    }
}
