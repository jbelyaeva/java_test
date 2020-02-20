package ru.stqa.pft.addressbook.appmanager;

import net.bytebuddy.utility.RandomString;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.internal.GroupsHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

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
  public void fillContactForm(@NotNull ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
   // attach(By.name("photo"), contactData.getPhoto());
  //  type(By.name("new_group"), contactData.getGroup());// нет в базе

    if (contactData.getGroups().size()>0) {
      Assert.assertTrue(contactData.getGroups().size()==1);
     new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
    }else if (creation && contactData.getGroups().size() == 0) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("[none]");
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));}
    }

  public void fillContactFormWithoutGroup(@NotNull ContactData contactData) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    // attach(By.name("photo"), contactData.getPhoto());
    // type(By.name("new_group"), contactData.getGroup());// нет в базе
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
  public void selectContactsById(int id)  {
    wd.findElement(By.cssSelector("input[value='"+id+"'")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void assertTrueDeletionContacts() {
       wd.switchTo().alert().accept();
  }

  public void selectContactModification(int id) {
    wd.findElement(By.xpath("//a[@href=\"edit.php?id="+id+"\"]")).click();
    }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public void create(ContactData contact, boolean b) {
    gotoAddNewContact();
    fillContactForm(contact,b);
    submitContactCreation();
    gotohome();
  }

   public void modify(ContactData contact) {
    selectContactsById(contact.getId());//выделить контакт для модификации
    selectContactModification(contact.getId()); //нажать карандаш
  //  fillContactForm(contact,false);//В БАЗЕ НЕТ ГРУППЫ
    fillContactFormWithoutGroup(contact);
    submitContactUpdate();
    gotohome();
  }

  public void delete(int index) {
   selectContacts(index);
   deleteSelectedContacts();
   assertTrueDeletionContacts();
  }
  public void delete(ContactData contact) {
    selectContactsById(contact.getId());
    deleteSelectedContacts();
    assertTrueDeletionContacts();
  }

  public void contactAddToGroup(ContactData contact, Groups groups) {
    gotohome();
    selectContactsById(contact.getId());
    if (groups.size()>0) {
      new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groups.iterator().next().getName());
    }else if (groups.size() == 0) {
      new Select(wd.findElement(By.name("to_group"))).selectByVisibleText("[none]");
    }
    else {Assert.assertFalse(isElementPresent(By.name("to_group")));}
    submitAddtoGroup();
    userAdd();
  //  gotohome();
  }

  private void userAdd() {
    click(By.xpath(".//a[contains(text(),'group page')]"));
  }

  private void submitAddtoGroup() {
    click(By.name("add"));
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

  public List<ContactData> list() {
    List<ContactData> contacts= new ArrayList<ContactData>();
    List<WebElement> elements= wd.findElements(By.name("entry"));
   for (WebElement element : elements) {
         List<WebElement> cells= element.findElements(By.xpath("td"));
         String lastname =cells.get(1).getText();
         String name =cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
   }
      return contacts;
  }
  public Contacts all() {
    Contacts contacts= new Contacts();
    List<WebElement> elements= wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells= element.findElements(By.xpath("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname =cells.get(1).getText();
      String name =cells.get(2).getText();
      String allPhones= cells.get(5).getText();
      String allEmails= cells.get(4).getText();
      String address = cells.get(3).getText();
      //contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname).withAddress(address).withEmail1(emails[0]).withEmail2(emails[1]).withEmail3(emails[2]).withHomephone(phones[0]).withMobilephone(phones[1]).withWorkphone(phones[2]));
      contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname)
              .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
    }
    return contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    String address=wd.findElement(By.name("address")).getAttribute("value");
    String email1=wd.findElement(By.name("email")).getAttribute("value");
    String email2=wd.findElement(By.name("email2")).getAttribute("value");
    String email3=wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(firstname).withLastname(lastname).withHomephone(home)
            .withMobilephone(mobile).withWorkphone(work).withAddress(address).withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox=wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
    WebElement row=checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells=row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Groups getGroupForContactCreation() {
    Groups groups = new DbHelper().groups();
    if (groups.size() == 0) {
      GroupData group = new GroupData().withName("test 5");
      new NavigationHelper(wd).groupPage();
      new GroupHelper(wd).create(group);
      groups = new DbHelper().groups();
    }
    return groups;
    }
}
