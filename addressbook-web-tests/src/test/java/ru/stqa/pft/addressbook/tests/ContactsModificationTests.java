package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactsModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions (){
    //если не сущ ни одного контакта сначала создать его
    if (app.contact().list().size()==0){
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      app.checkAndCreateGroup(new GroupData().withName("test1"));
      app.contact().create(new ContactData().withName("Саша").withLastname("Иванов").withAddress("г. Москва ул.Строителей д.7 кв 9")
              .withHomephone("1111111111111").withMobilephone("22222222222").withEmail("1111@11.com").withGroup("test1"),true);
    }
  }
  @Test
public void testContactsModification(){
  Set<ContactData> before = app.contact().all();
  ContactData modifiedContact = before.iterator().next();
 // int index=before.size()-1;
  ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("1").withLastname("2").withAddress("3")
                         .withHomephone("4").withMobilephone("5").withEmail("6");
  app.contact().modify(contact);
  Set<ContactData> after = app.contact().all();
  Assert.assertEquals(after.size(),before.size());
  before.remove(modifiedContact);
  before.add(contact);
  Assert.assertEquals(before,after);
}
}
