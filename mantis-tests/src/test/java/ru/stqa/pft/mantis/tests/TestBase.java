package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

  protected static final ApplicationManager app
                   = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

 // @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.php.bak");

  }

 // @AfterSuite
  public void tearDown() throws Exception {
    app.ftp().restore("config_inc.php.bak","config_inc.php");
    app.stop();
  }

  public static boolean isIssueOpen(int issueId) throws  MalformedURLException, ServiceException, RemoteException {

    boolean isFixed = false;
    Set<Project> projects = app.soap().getProject();
    Issue issue = new Issue().withId(issueId);
    MantisConnectPortType mc = app.soap().getMantisConnect();
    ObjectRef status =  mc.mc_issue_get("administrator","root", BigInteger.valueOf(issueId)).getStatus();
    if (status.getName().equals("fixed")) {
      isFixed = true;
    }
    return isFixed;
  }

  public static void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}


