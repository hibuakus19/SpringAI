package com.KRSGacorV2.UASSpringAI.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class AiService {
    private final ChatClient chatClient;

    public AiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    private final boolean USE_MOCK = true; //rubah ke false jika sudah ada API Key yang benar

    public String helloAi() {
        if (USE_MOCK) {
            return "Hello World! (Mode Mock: Sistem berjalan lancar tanpa kuota API)";
        }
        
        return chatClient.prompt()
                .user("Katakan: Hello World, saya Spring AI dengan Gemini!")
                .call()
                .content();
    }

    public String askAi(String question) {
        if (USE_MOCK) {
            return generateMockResponse(question);
        }

        try {
            String prompt = """
                    Kamu adalah asisten dosen.
                    Jawab singkat: {question}
                    """;
            return chatClient.prompt()
                    .user(u -> u.text(prompt).param("question", question))
                    .call()
                    .content();
        } catch (Exception e) {
            System.err.println("Error koneksi ke AI: " + e.getMessage());
            return "⚠️ Server AI sedang sibuk, beralih ke jawaban simulasi: \n\n" + generateMockResponse(question);
        }
    }
    private String generateMockResponse(String question) {
        String[] templates = {
            "Berdasarkan teori Teknologi Informasi, \"%s\" adalah konsep yang fundamental dalam pengembangan sistem modern.",
            "Pertanyaan yang bagus mengenai \"%s\". Dalam konteks akademik, hal ini berkaitan erat dengan efisiensi algoritma.",
            "Secara singkat, \"%s\" dapat dijelaskan sebagai mekanisme integrasi data yang terstruktur.",
            "Menurut referensi jurnal terbaru, \"%s\" merupakan topik yang sedang hangat diperbincangkan di dunia IT."
        };

        String template = templates[new Random().nextInt(templates.length)];
        return String.format(template, question);
    }
}