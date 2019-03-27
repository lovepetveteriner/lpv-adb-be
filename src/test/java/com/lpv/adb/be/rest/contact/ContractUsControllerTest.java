package com.lpv.adb.be.rest.contact;

import com.lpv.adb.be.helper.TestUtil;
import com.lpv.adb.be.infrastucture.email.EmailService;
import com.lpv.adb.be.infrastucture.email.LpvEmailProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author selimssevgi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContractUsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmailService emailService;

  @Autowired
  private LpvEmailProperties lpvEmailProperties;

  @Test
  public void receivesSendEmailRequests() throws Exception {
    SendEmailReq sendEmailReq = new SendEmailReq();
    sendEmailReq.setEmail("selimssevgi@gmail.com");
    sendEmailReq.setName("selimssevgi");
    sendEmailReq.setMessage("hello-world");
    sendEmailReq.setSubject("hw");

    mockMvc.perform(post("/non-secured/send-email")
    .contentType(MediaType.APPLICATION_JSON_UTF8)
    .content(TestUtil.toJsonBytes(sendEmailReq)))
        .andExpect(status().isOk());

    verify(emailService, times(1))
        .sendSimple(argThat(simpleEmailCmd -> {
          assertThat(simpleEmailCmd.getTo()).isEqualTo(lpvEmailProperties.getSystemTo());
          assertThat(simpleEmailCmd.getSubject())
              .isEqualTo(lpvEmailProperties.getStaticSiteMessageSubjectPrefix() + "hw");
          assertThat(simpleEmailCmd.getText()).contains("email: selimssevgi@gmail.com");
          assertThat(simpleEmailCmd.getText()).contains("isim: selimssevgi");
          assertThat(simpleEmailCmd.getText()).contains("mesaj: hello-world");
          return true;
        }));
  }
}
