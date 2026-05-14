package com.example.HMS_AI.Service;

import com.example.HMS_AI.DTOs.Request.AiQuery;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Enum.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {
    private final ChatClient.Builder chatClientBuilder;

    public ResponseEntity<GlobalResponseHandler> summarizePrescription(AiQuery query) {
        ChatClient chatClient = chatClientBuilder.build();

        String prompt = """
                You are a medical assistant.

                Summarize the prescription in simple language.
                Explain medicines, dosage, precautions and purpose.

                Prescription:
                """ + query;

        String message = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        return ResponseEntity.ok(GlobalResponseHandler.builder()
                .data(message)
                .status(RequestStatus.SUCCESS).build());
    }

    public ResponseEntity<GlobalResponseHandler> analyzeReport(AiQuery query) {
        ChatClient chatClient = chatClientBuilder.build();

        String prompt = """
                You are a medical AI assistant.

                Analyze the following medical report.
                Explain abnormalities and possible concerns
                in very simple language.

                Do not provide final diagnosis.

                Medical Report:
                """ + query;

        String message = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        return ResponseEntity.ok(GlobalResponseHandler.builder()
                .data(message)
                .status(RequestStatus.SUCCESS).build());

    }
}
