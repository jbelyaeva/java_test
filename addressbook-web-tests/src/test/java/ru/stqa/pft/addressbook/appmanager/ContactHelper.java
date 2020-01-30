package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());

   if (creation) {
     new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
   }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));}
  }
    public boolean isElementPresent(By locator){
       try {wd.findElement(locator);
            return true;}
       catch (NoSuchElementException ex){return false;}
    }


  public void gotoAddNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContacts(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
   }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void assertTrueDeletionContacts() {
       wd.switchTo().alert().accept();
  }

  public void selectContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact, boolean b) {
    gotoAddNewContact();
    fillContactForm(contact,b);
    submitContactCreation();
    gotohome();
  }

  public void gotohome() {
     click(By.linkText("home"));
   }

  public boolean isTereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();// получаем размер списка элементов
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts= new ArrayList<ContactData>();
    List<WebElement> elements= wd.findElements(By.name("entry"));
  //  List<WebElement> elements= wd.findElements(By.xpath("//tr[@name='entry']//td[2]"));
  //  List<WebElement> elements1= wd.findElements(By.xpath("//tr[@name='entry']//td[3]"));
    for (WebElement element : elements){
      String lastname = element.getText();
      String name = element.getText();
      String id=element.findElement(By.tagName("input")).getAttribute("value");
     // String name=element.findElements(By.xpath("//tr[@name='entry']//td[3]")).g;
      ContactData contact= new ContactData(id,"1", "2", null, null,null,null,null);
      contacts.add(contact);
    }
    return contacts;
  }
}
