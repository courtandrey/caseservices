package com.github.courtandrey.casegenerator;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Component
public class CaseGenerator {
    Iterable<Decision> generate() {
        Decision d = new Decision();
        d.setId(UUID.randomUUID().toString());
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/home/andrey/IdeaProjects/TextGeneration/venv/bin/python3",
                    (new File(".").getCanonicalPath()) + "/src/main/python/case_gen.py");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();
            BufferedReader reader = Files.newBufferedReader(Path.of((new File(".").getCanonicalPath()) +
                    "/src/main/python/generated_case.txt"));
            StringBuilder text = new StringBuilder();
            while (reader.ready()) {
                text.append(reader.readLine());
            }
            reader.close();
            d.setText(text.toString());
        } catch (Exception ex) {
            d.setText("EXCEPTION: " + ex);
        }
        return List.of(new Decision());
    }
}