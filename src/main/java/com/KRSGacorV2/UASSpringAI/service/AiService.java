package com.KRSGacorV2.UASSpringAI.service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String helloAi() {
        return chatClient.prompt()
                .user("Katakan: Hello World, saya Spring AI dengan Gemini!")
                .call()
                .content();
    }

    public String askAi(String question) {
        String prompt = """
                Kamu adalah asisten dosen bidang Teknologi Informasi.
                Jawablah secara singkat, jelas, dan akademik
                menggunakan bahasa Indonesia.

                Pertanyaan: {question}
                """;

        return chatClient.prompt()
                .user(u -> u.text(prompt).param("question", question))
                .call()
                .content();
    }
}