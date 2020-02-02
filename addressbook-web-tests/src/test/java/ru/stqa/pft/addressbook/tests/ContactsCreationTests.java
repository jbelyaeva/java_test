package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactsCreation() throws Exception {
    //проверить, что сущ группа test1, если нет,то создать её
    app.checkAndCreateGroup(new GroupData().withName("test1"));

    Set<ContactData> before = app.contact().all();
    ContactData contact= new ContactData().withName("Саша").withLastname("Иванов")
            .withAddress("г. Москва ул.Строителей д.7 кв 9").withHomephone("1111111111111")
            .withMobilephone("22222222222").withEmail("1111@11.com").withGroup("test1");
    app.contact().create(contact,true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size()+1);
   // contact.withId(after.stream().max( (Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());//выдает идентификатор, переводит объект в чиcло, опредлеяет max
    before.add(contact);
    Assert.assertEquals(before,after);
        }

}
