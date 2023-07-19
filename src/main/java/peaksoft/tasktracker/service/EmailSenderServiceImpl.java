package peaksoft.tasktracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl {

    private final JavaMailSender javaMailSender;


    public void sendEmail(List<String> toEmail, String subject, String body) {
        for (String recipient : toEmail) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("abduvohobuulumatmusa@gmail.com");
            message.setTo(recipient);
            message.setText(body);
            message.setSubject(subject);
            javaMailSender.send(message);
            System.out.println("Mail sent successfully to: " + recipient);
        }
    }
}
