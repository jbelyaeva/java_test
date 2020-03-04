package ru.stqa.pft.rest.tests;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.Set;



public class TestBase {

  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=1000")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());

  }
  public static Executor getExecutor(){
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json?limit=1000")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);//анализируем строчку
    return parsed.getAsJsonObject().get("issue_id").getAsInt();//идентификатор баг-репортера
  }


  public static boolean isIssueOpen(int issueId) throws IOException {

     String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=1000")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());//множество баг репортов
    Issue issue1= issue.iterator().next().withId(issueId);
    //для отладки
    String status=issue1.getState_name();

    if (issue1.getState_name().equals("Resolved")) {
      return true;
     }
    return false;
  }

  public static void skipIfNotFixed(int issueId) throws IOException {
    if (!isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}


