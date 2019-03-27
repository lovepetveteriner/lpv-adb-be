package com.lpv.adb.be.rest.contact;

import com.lpv.adb.be.infrastucture.email.EmailService;
import com.lpv.adb.be.infrastucture.email.LpvEmailProperties;
import com.lpv.adb.be.infrastucture.email.SimpleEmailCmd;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the requests coming from static site contact form.
 *
 * @author selimssevgi
 */
@RestController
@AllArgsConstructor
public class ContractUsController {

  private final EmailService emailService;

  private final LpvEmailProperties lpvEmailProperties;

  /**
   * Forwards the message request as email to system owners.
   */
  @PostMapping("/non-secured/send-email")
  public void sendMail(@RequestBody SendEmailReq emailReq) {
    String text =
        "isim: " + emailReq.getName() + "\n"
            + "email: " + emailReq.getEmail() + "\n"
            + "mesaj: " + emailReq.getMessage();

    SimpleEmailCmd emailCommand = new SimpleEmailCmd();
    emailCommand.setTo(lpvEmailProperties.getSystemTo());
    emailCommand.setSubject(lpvEmailProperties.prefixWithSiteMessageSubject(emailReq.getSubject()));
    emailCommand.setText(text);
    emailService.sendSimple(emailCommand);
  }
}
