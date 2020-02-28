package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMassage;
import ru.stqa.pft.mantis.model.UserData;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{
  private String targetUserName;
  private int targetUserId;
  private String targetUserPassword;

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }
  @BeforeMethod
  public void Precondition() throws IOException, MessagingException {
      UserData user=app.db().users().iterator().next();
      targetUserName=user.getUsername();
      targetUserId=user.getId();
      targetUserPassword=user.getPassword();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {

    app.doingAdmin().startChange("administrator", "root",targetUserId);
    List<MailMassage> mailMassages = app.mail().waitForMail(3,10000);

 /*   String confirmationLink = findConfirmationLink(mailMassages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user,password));*/
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
