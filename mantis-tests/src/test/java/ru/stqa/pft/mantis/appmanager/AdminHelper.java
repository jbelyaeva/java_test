package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class AdminHelper extends HelperBase {
  public AdminHelper(ApplicationManager app) {super(app);}
  
  public void startChange(String username, String password,int id) {
    wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
    type(By.name("username"),username);
    click(By.xpath("//input[@type='submit']"));
    type(By.name("password"),password);
    click(By.xpath("//input[@type='submit']"));
    click(By.xpath("//a[@href='/mantisbt-2.23.0/manage_overview_page.php']"));
    click(By.xpath("//a[@href='/mantisbt-2.23.0/manage_user_page.php']"));
    click(By.xpath("//a[@href='manage_user_edit_page.php?user_id="+id+"']"));
    click(By.xpath("//input[@class='btn btn-primary btn-white btn-round']"));
    //   click(By.xpath("//span[@class='user-info']"));
    //  click(By.xpath("//input[@class='/mantisbt-2.23.0/logout_page.php']"));

  }
  public void setPassword(String userEditLink, UserData user) {
    wd.get(userEditLink);
    type(By.name("password"), user.getPassword());
    type(By.name("password_confirm"), user.getPassword());
    click(By.cssSelector("input[value=\"Update User\"]"));
  }


}
