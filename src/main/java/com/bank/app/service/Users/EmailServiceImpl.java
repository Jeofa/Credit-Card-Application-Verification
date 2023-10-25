package com.bank.app.service.Users;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.sun.mail.smtp.SMTPTransport;

@Component
public class EmailServiceImpl  {

    @Autowired
    private JavaMailSender emailSender;
    
    private String html;
	 private static final String SMTP_SERVER = "smtp.gmail.com";
	    private static final String USERNAME = "bnk.bank.ke@gmail.com";
	    private static final String PASSWORD = "gqofqvkiocwzrqzx";

    private static final String EMAIL_FROM = "bnk.bank.ke@gmail.com";
    private static final String EMAIL_TO = "bnk.bank.ke@gmail.com, jeffochieng00@gmail.com";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP (HTML)";
    private static final String EMAIL_TEXT = "<h1>Hello Java Mail \n <strong>ABC123</strong></h1>";

    public void sendSimpleMessage(
      String to, String subject, String text) {
    	Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, null);
//        SimpleMailMessage message = new SimpleMailMessage(); 
//        message.setFrom("bnk.bank.ke@gmail.com");
//        message.setTo(to); 
//        message.setSubject(subject); 
//        message.setText(text);
//        emailSender.send(message);
        Message message = new MimeMessage(session);
        try {
			message.setFrom(new InternetAddress(EMAIL_FROM));
		

        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(EMAIL_TO, false));

        message.setSubject(EMAIL_SUBJECT);
//        message.setText(text);
       message.setDataHandler(new DataHandler(new HTMLDataSource(text)));
       
       SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
		
		// connect
       t.connect(SMTP_SERVER, USERNAME, PASSWORD);
//		
		// send
       t.sendMessage(message, message.getAllRecipients());

       System.out.println("Response: " + t.getLastServerResponse());

       t.close();
       } catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    static class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("html message is null!");
            return new ByteArrayInputStream(html.getBytes());
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public String getName() {
            return "HTMLDataSource";
        }
    }
}