package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactsCreation() throws Exception {
    //проверить, что сущ группа test1, если нет,то создать её
    app.checkAndCreateGroup(new GroupData().withName("test1"));

    Contacts before = app.contact().all();
    File photo= new File("src/test/resources/stru.png");
    ContactData contact= new ContactData().withName("Саша").withLastname("Иванов")
            .withAddress("г. Москва ул.Строителей д.7 кв 9").withHomephone("1111111111111")
            .withMobilephone("22222222222").withEmail1("1111@11.com").withEmail2("222@gh.com").withEmail3("ghj@gh.ru").withPhoto(photo).withGroup("test1");
    app.contact().create(contact,true);
    Contacts after = app.contact().all();

    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
        }
  /* @Test
   public void testCurrentDir(){
    File currentDir=new File(".");
    System.out.println(currentDir.getAbsolutePath());
     File photo= new File("src/test/resources/stru.png");
     System.out.println(photo.getAbsolutePath());
     System.out.println(photo.exists());
  }*/

}
