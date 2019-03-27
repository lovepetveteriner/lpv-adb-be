package com.lpv.adb.be.infrastucture.email;

import lombok.Data;

/**
 * @author selimssevgi
 */
@Data
public class SimpleEmailCmd {
  private String to;
  private String subject;
  private String text;
}
