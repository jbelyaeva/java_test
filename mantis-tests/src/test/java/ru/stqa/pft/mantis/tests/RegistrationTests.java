package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMassage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{
 // @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }
  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String user = String.format("user-%s", now);
    String password = "password";
    String email = String.format("user-%s@localhost", now);
    app.james().createUser(user, password);
    app.registration().start(user, email);
    List<MailMassage> mailMassages= app.james().waitForMail(user,password,120000);
    String confirmationLink = findConfirmationLink(mailMassages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user,password));
  }

  private String findConfirmationLink(List<MailMassage> mailMassages, String email) {
    MailMassage mailMassage = mailMassages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex= VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
   return regex.getText(mailMassage.text);
  }

 // @AfterMethod(alwaysRun = true)
  public void stoptMailServer(){
    app.mail().stop();
  }
}
