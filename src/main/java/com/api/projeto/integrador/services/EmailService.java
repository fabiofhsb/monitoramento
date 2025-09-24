package com.api.projeto.integrador.services;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviarAlerta(String email, String cidade, String temperatura) {
        String remetente = "temperatura.compostbarn@gmail.com";
        String senha = "zbtphasjdsfofqsg";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session sessao = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        sessao.setDebug(true);

        try {
            Message mensagem = new MimeMessage(sessao);
            mensagem.setFrom(new InternetAddress(remetente));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            mensagem.setSubject("🚨 Alerta de Temperatura Elevada");

            String html = "<!DOCTYPE html>" +
                    "<html><head><meta charset='UTF-8'>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; }" +
                    ".container { background-color: #fff; border-radius: 8px; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
                    +
                    ".header { font-size: 20px; color: #d32f2f; margin-bottom: 10px; }" +
                    ".content { font-size: 16px; color: #333; }" +
                    ".footer { margin-top: 20px; font-size: 12px; color: #777; }" +
                    "</style></head><body>" +
                    "<div class='container'>" +
                    "<div class='header'>🚨 Alerta de Temperatura Elevada</div>" +
                    "<div class='content'>" +
                    "<p>Atenção!</p>" +
                    "<p>Detectamos uma temperatura acima do limite no <strong>Galpão</strong></p>" +
                    "<p><strong>Temperatura registrada:</strong> <span style='color:#d32f2f;'>" + temperatura
                    + "°C</span></p>" +
                    "<p>Recomenda-se verificar as condições do ambiente e tomar medidas preventivas para garantir o bem-estar dos animais.</p>"
                    +
                    "<p>Este alerta foi gerado automaticamente pelo sistema de monitoramento compost barn.</p>" +
                    "</div>" +
                    "<div class='footer'>" +
                    "<p>© 2025 Sistema de Monitoramento | Este é um e-mail automático, não responda.</p>" +
                    "</div></div></body></html>";

            mensagem.setContent(html, "text/html");

            Transport.send(mensagem);
            System.out.println("✅ E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
