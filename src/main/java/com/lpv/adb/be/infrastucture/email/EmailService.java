package com.lpv.adb.be.infrastucture.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Sends emails using JavaMailSender api.
 * @author selimssevgi
 */
@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

  private final JavaMailSender mailSender;

  private final MailProperties mailProperties;

  /**
   * Sends simple email.
   */
  public void sendSimple(SimpleEmailCmd emailCommand) {
    log.info("Attempting to send email to {}", emailCommand.getTo());
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(mailProperties.getUsername());
    message.setTo(emailCommand.getTo());
    message.setSubject(emailCommand.getSubject());
    message.setText(emailCommand.getText());
    mailSender.send(message);
    log.info("Sent email to {}", emailCommand.getTo());
  }
}
