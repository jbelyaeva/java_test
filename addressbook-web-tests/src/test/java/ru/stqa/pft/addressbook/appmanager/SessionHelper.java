package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
  private WebDriver wd;

  public SessionHelper(WebDriver wd) {
    this.wd = wd;
  }
  public void login(String username, String passowd) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(passowd);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }
}
