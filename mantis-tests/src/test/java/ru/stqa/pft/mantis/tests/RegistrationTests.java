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
  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }
  @Test
  public void testRegistration() throws IOException, MessagingException {
    String user = "user"+Math.round(Math.random() * 1000);
    String email = user+"@localhost.localdomain";
    String password = "password";
    app.registration().start(user, email);
  List<MailMassage> mailMassages= app.mail().waitForMail(2,10000);
    String confirmationLink = findConfirmationLink(mailMassages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user,password));
  }

  private String findConfirmationLink(List<MailMassage> mailMassages, String email) {
    MailMassage mailMassage = mailMassages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex= VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
   return regex.getText(mailMassage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stoptMailServer(){
    app.mail().stop();
  }
}
