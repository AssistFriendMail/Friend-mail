package com.friend.mail.demo.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class MailProcessorService {
    public void processEmail(MimeMessage message) {
        try {
            var subject = message.getSubject();
            var from = message.getFrom()[0].toString();
            var content = message.getContent().toString();

            System.out.println("📩 Novo e-mail:");
            System.out.println("De: " + from);
            System.out.println("Assunto: " + subject);
            System.out.println("Conteúdo: " + content);

            // TODO: Filtrar spam, classificar e enviar para fila
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
