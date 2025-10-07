package com.api.projeto.integrador.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class EmailService {

    private static final String API_KEY = "re_eWSUnm6S_LjVf1z4Rb2om3jJbVZdiohJx"; // Substitua pela sua chave
    private static final String RESEND_URL = "https://api.resend.com/emails";

    public void enviarAlerta(String email, String tipo, String valor) {
        String assunto = tipo.equals("umidade") ? "💧 Alerta de Umidade Baixa" : "🚨 Alerta de Temperatura Elevada";
        String html = tipo.equals("umidade")
                ? gerarHtmlUmidade(valor)
                : gerarHtmlTemperatura(valor);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        Map<String, Object> body = new HashMap<>();
        body.put("from", "Sistema de Alerta <onboarding@resend.dev>");
        body.put("to", email);
        body.put("subject", assunto);
        body.put("html", html);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(RESEND_URL, request, String.class);
            System.out.println("✅ Status do envio: " + response.getStatusCode());
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar e-mail: " + e.getMessage());
        }
    }

    private String gerarHtmlTemperatura(String valor) {
        return "<!DOCTYPE html>" +
                "<html><head><meta charset='UTF-8'>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; }"
                +
                ".container { background-color: #fff; border-radius: 8px; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
                +
                ".header { font-size: 20px; color: #d32f2f; margin-bottom: 10px; }" +
                ".content { font-size: 16px; color: #333; }" +
                ".footer { margin-top: 20px; font-size: 12px; color: #777; }" +
                "</style></head><body>" +
                "<div class='container'>" +
                "<div class='header'>Alerta de Temperatura Elevada!</div>" +
                "<div class='content'>" +
                "<p>Atenção!</p>" +
                "<p>Detectamos uma temperatura acima do limite no <strong>Galpão.</strong></p>"
                +
                "<p><strong>Temperatura registrada:</strong> <span style='color:#d32f2f;'>" +
                valor
                + "°C.</span></p>" +
                "<p>Recomenda-se verificar as condições do ambiente e tomar as medidas preventivas para garantir o bem-estar dos animais.</p>"
                +
                "<p>Este alerta foi gerado automaticamente pelo sistema de monitoramento compost barn.</p>"
                +
                "</div>" +
                "<div class='footer'>" +
                "<p>© 2025 Sistema de Monitoramento | Este é um e-mail automático, não responda.</p>"
                +
                "</div></div></body></html>";
    }

    private String gerarHtmlUmidade(String valor) {
        return "<!DOCTYPE html>" +
                "<html><head><meta charset='UTF-8'>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; }"
                +
                ".container { background-color: #fff; border-radius: 8px; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
                +
                ".header { font-size: 20px; color: #d32f2f; margin-bottom: 10px; }" +
                ".content { font-size: 16px; color: #333; }" +
                ".footer { margin-top: 20px; font-size: 12px; color: #777; }" +
                "</style></head><body>" +
                "<div class='container'>" +
                "<div class='header'>Alerta de Umidade Baixa!</div>" +
                "<div class='content'>" +
                "<p>Atenção!</p>" +
                "<p>Detectamos que a umidade está abaixo do limite no <strong>Galpão.</strong></p>"
                +
                "<p><strong>Umidade registrada:</strong> <span style='color:#d32f2f;'>" +
                valor
                + "%.</span></p>" +
                "<p>Recomenda-se verificar as condições do ambiente e tomar as medidas previstas para garantir o bem-estar dos animais.</p>"
                +
                "</div>" +
                "<div class='footer'>" +
                "<p>© 2025 Sistema de Monitoramento | Este é um e-mail automático, não responda.</p>"
                +
                "</div></div></body></html>";
    }

    /*
     * public void enviarAlerta(String email, String cidade, String temperatura) {
     * String remetente = "temperatura.compostbarn@gmail.com";
     * String senha = "zbtphasjdsfofqsg";
     * 
     * Properties props = new Properties();
     * props.put("mail.smtp.host", "smtp.gmail.com");
     * props.put("mail.smtp.port", "587");
     * props.put("mail.smtp.auth", "true");
     * props.put("mail.smtp.starttls.enable", "true");
     * 
     * Session sessao = Session.getInstance(props, new Authenticator() {
     * protected PasswordAuthentication getPasswordAuthentication() {
     * return new PasswordAuthentication(remetente, senha);
     * }
     * });
     * 
     * sessao.setDebug(true);
     * 
     * try {
     * Message mensagem = new MimeMessage(sessao);
     * mensagem.setFrom(new InternetAddress(remetente));
     * mensagem.setRecipients(Message.RecipientType.TO,
     * InternetAddress.parse(email));
     * mensagem.setSubject("Alerta de Temperatura Elevada");
     * 
     * String html = "<!DOCTYPE html>" +
     * "<html><head><meta charset='UTF-8'>" +
     * "<style>" +
     * "body { font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; }"
     * +
     * ".container { background-color: #fff; border-radius: 8px; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
     * +
     * ".header { font-size: 20px; color: #d32f2f; margin-bottom: 10px; }" +
     * ".content { font-size: 16px; color: #333; }" +
     * ".footer { margin-top: 20px; font-size: 12px; color: #777; }" +
     * "</style></head><body>" +
     * "<div class='container'>" +
     * "<div class='header'>Alerta de Temperatura Elevada!</div>" +
     * "<div class='content'>" +
     * "<p>Atenção!</p>" +
     * "<p>Detectamos uma temperatura acima do limite no <strong>Galpão.</strong></p>"
     * +
     * "<p><strong>Temperatura registrada:</strong> <span style='color:#d32f2f;'>" +
     * temperatura
     * + "°C.</span></p>" +
     * "<p>Recomenda-se verificar as condições do ambiente e tomar medidas preventivas para garantir o bem-estar dos animais.</p>"
     * +
     * "<p>Este alerta foi gerado automaticamente pelo sistema de monitoramento compost barn.</p>"
     * +
     * "</div>" +
     * "<div class='footer'>" +
     * "<p>© 2025 Sistema de Monitoramento | Este é um e-mail automático, não responda.</p>"
     * +
     * "</div></div></body></html>";
     * 
     * mensagem.setContent(html, "text/html");
     * 
     * Transport.send(mensagem);
     * System.out.println("✅ E-mail enviado com sucesso!");
     * } catch (MessagingException e) {
     * e.printStackTrace();
     * }
     * }
     */
}
