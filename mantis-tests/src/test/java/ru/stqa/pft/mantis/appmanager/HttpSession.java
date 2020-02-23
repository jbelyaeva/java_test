package ru.stqa.pft.mantis.appmanager;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
  private CloseableHttpClient httpclient;
  private ApplicationManager app;
  public HttpSession(ApplicationManager app){
    this.app=app;
    //создается httpclient, который автоматически перенаправляет все направления
    httpclient= HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }
    //какой логин
  public boolean login(String username, String password) throws IOException{
    HttpPost post= new HttpPost(app.getProperty("web.baseUrl")+"/login.php");//создается будущий запрос (пока пустой)
    List<BasicNameValuePair> params= new ArrayList<>();
    params.add(new BasicNameValuePair("username",username));
    params.add(new BasicNameValuePair("password",password));
    params.add(new BasicNameValuePair("secure_session","on"));
    params.add(new BasicNameValuePair("return","index.php"));
    post.setEntity(new UrlEncodedFormEntity(params));//заранее созданый запрос
    CloseableHttpResponse response=httpclient.execute(post);//запрос выполняется
    String body=geTextFrom(response);
    return body.contains(String.format("<span class=\"italic\">%s</span>",username));
  }

  //получить текст ответа
  private String geTextFrom(CloseableHttpResponse response) throws IOException{
    try{
      return EntityUtils.toString(response.getEntity());
    }finally {
      response.close();
    }
  }
  //какой пользователь залогинен
  public boolean isLoggedInAs(String username) throws IOException{
    HttpGet get=new HttpGet(app.getProperty("web.baseUrl")+"/index.php");//зайти на главную страницу
    CloseableHttpResponse response=httpclient.execute(get);
    String body=geTextFrom(response);
    return  body.contains(String.format("<span class=\"italic\">%s</span>",username));
  }
}
