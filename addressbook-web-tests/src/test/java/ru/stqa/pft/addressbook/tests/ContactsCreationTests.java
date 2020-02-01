package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactsCreation() throws Exception {
    //проверить, что сущ группа test1, если нет,то создать её
    app.checkAndCreateGroup(new GroupData().withName("test1"));
    List<ContactData> before = app.contact().list();
    ContactData contact= new ContactData().withName("Саша").withLastname("Иванов")
            .withAddress("г. Москва ул.Строителей д.7 кв 9").withHomephone("1111111111111")
            .withMobilephone("22222222222").withEmail("1111@11.com").withGroup("test1");
    app.contact().create(contact,true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size()+1);
    contact.withId(after.stream().max( (Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);

    Comparator<? super ContactData> byId=(g1,g2)-> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
        }

}
