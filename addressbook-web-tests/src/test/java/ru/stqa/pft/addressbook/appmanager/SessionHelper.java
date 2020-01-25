package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
    public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String passowd) {
    type(By.name("user"),username);
    type(By.name("pass"),passowd);
    click(By.xpath("//input[@value='Login']"));
  }
}
