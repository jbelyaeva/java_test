package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.stqa.pft.rest.tests.TestBase.isIssueOpen;
import static ru.stqa.pft.rest.tests.TestBase.skipIfNotFixed;

public class RestTest extends TestBase {
  public int Id=2535;

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues=getIssues();
    Issue newIssue=new Issue().withSubject("Test issue").withDescription("New test issue").withState_name("Resolved");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues=getIssues();
    oldIssues.add(newIssue.withId(issueId));
   assertEquals(newIssues, oldIssues);
  }
  @Test
  public void testIssueStatus() throws IOException {
    skipIfNotFixed(Id);
    assertTrue(isIssueOpen(Id));
  }


}
