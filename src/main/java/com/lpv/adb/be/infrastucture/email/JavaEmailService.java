package com.lpv.adb.be.infrastucture.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(name = "lpv.email.skip", havingValue = "false", matchIfMissing = true)
public class JavaEmailService implements EmailService {

  private final JavaMailSender mailSender;

  private final MailProperties mailProperties;

  {
    log.info("Using {} as email service", this.getClass().getSimpleName());
  }

  /**
   * Sends simple email.
   */
  @Override
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
