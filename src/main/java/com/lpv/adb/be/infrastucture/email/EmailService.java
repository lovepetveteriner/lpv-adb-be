package com.lpv.adb.be.infrastucture.email;

/**
 * Defines operations for email functionality.
 *
 * @author selimssevgi
 */
public interface EmailService {

  void sendSimple(SimpleEmailCmd emailCommand);

}
