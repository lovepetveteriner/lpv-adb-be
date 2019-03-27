package com.lpv.adb.be.infrastucture.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Properties to customize system email behaviors.
 *
 * @author selimssevgi
 */
@ConfigurationProperties("lpv.mail")
@Component
@Data
public class LpvEmailProperties {
  private String systemTo = "lovepetveteriner@gmail.com";

  /**
   * It is used as subject of the email when delivering messages from static site form.
   */
  private String staticSiteMessageSubjectPrefix =  "ZMV: ";

  public String prefixWithSiteMessageSubject(String other) {
    String nonNull = Objects.requireNonNullElse(other, "N/A");
    return getStaticSiteMessageSubjectPrefix() + nonNull;
  }
}
