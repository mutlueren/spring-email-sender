package rc.mail.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import rc.mail.feedback.mailconfig.EmailCfg;
import rc.mail.feedback.model.Feedback;

import javax.validation.ValidationException;

@Service
public class FeedBackService {

    @Autowired
    private EmailCfg emailCfg;

    public void sendFeedback(Feedback feedback, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }

        try{
            // Create a mail sender
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(this.emailCfg.getHost());
            mailSender.setPort(this.emailCfg.getPort());
            mailSender.setUsername(this.emailCfg.getUsername());
            mailSender.setPassword(this.emailCfg.getPassword());

            // Create an email instance
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(feedback.getEmail());
            mailMessage.setTo("rc@feedback.com");
            mailMessage.setSubject("New feedback from " + feedback.getName());
            mailMessage.setText(feedback.getFeedback());

            // Send mail
            mailSender.send(mailMessage);
        }catch(Exception e){
            System.out.println("Error occured: "+e.getMessage());
        }
    }
}
