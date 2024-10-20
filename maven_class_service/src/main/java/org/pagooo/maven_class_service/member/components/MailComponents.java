package org.pagooo.maven_class_service.member.components;


import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailComponents {
    private final JavaMailSender javaMailSender;
    public void sendMailTest(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("hyomyang@gmail.com");
        msg.setSubject("Java Mail Sender Test");
        msg.setText("Test Test Test");

        javaMailSender.send(msg);

    }

    public boolean sendMail(String mail, String subject, String text){

        boolean result = false;

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };

        try {
            javaMailSender.send(msg);
            result = true;
        } catch (MailException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
