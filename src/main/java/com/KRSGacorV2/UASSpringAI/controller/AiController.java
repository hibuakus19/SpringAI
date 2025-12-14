package com.KRSGacorV2.UASSpringAI.controller;

import com.KRSGacorV2.UASSpringAI.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/hello")
    public String hello() {
        return aiService.helloAi();
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        return aiService.askAi(question);
    }
}
