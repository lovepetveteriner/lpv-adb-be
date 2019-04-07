package com.lpv.adb.be.infrastucture.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Does nothing except than reporting the email request content.
 * Can be used while running tests, or in case of disabling email service completely.
 *
 * @author selimssevgi
 */
@Service
@Slf4j
@ConditionalOnProperty(name = "lpv.email.skip", havingValue = "true")
public class DoNothingEmailService implements EmailService {

  public DoNothingEmailService() {
    log.info("Using {} as email service", this.getClass().getSimpleName());
  }

  @Override
  public void sendSimple(SimpleEmailCmd emailCommand) {
    log.info("Pretending to send email from request: {}", emailCommand);
  }
}
