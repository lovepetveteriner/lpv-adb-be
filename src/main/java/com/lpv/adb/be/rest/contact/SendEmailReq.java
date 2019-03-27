package com.lpv.adb.be.rest.contact;

import lombok.Data;

@Data
public class SendEmailReq {
  private String name;
  private String email;
  private String subject;
  private String message;
}
