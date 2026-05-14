package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.AiQuery;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("HMS-Ai/AskAi")
public class AiController {
    private final AiService aiService;

    public AiController(AiService aiService){
        this.aiService = aiService;
    }

    @PostMapping("/summarize-prescription")
    public ResponseEntity<GlobalResponseHandler> summarizePrescription(@RequestBody AiQuery query){
        return aiService.summarizePrescription(query);
    }

    @PostMapping("/analyze-report")
    public ResponseEntity<GlobalResponseHandler> analyzeReport(@RequestBody AiQuery query){
        return aiService.analyzeReport(query);
    }

}
