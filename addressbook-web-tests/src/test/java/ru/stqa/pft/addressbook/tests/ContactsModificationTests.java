package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactsModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions (){
    //если не сущ ни одного контакта сначала создать его
    if (app.contact().list().size()==0){
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      app.checkAndCreateGroup(new GroupData().withName("test1"));
      app.contact().create(new ContactData().withName("Саша").withLastname("Сиванов").withAddress("г. Москва ул.Строителей д.7 кв 9")
              .withHomephone("1111111111111").withMobilephone("22222222222").withWorkphone("33333333")
              .withEmail1("1111@11.com").withEmail2("222@gh.com").withEmail3("ghj@gh.ru").withGroup("test1"),true);
    }
  }
  @Test
public void testContactsModification(){
 // Contacts before = app.contact().all();
    Contacts before=app.db().contacts();
  ContactData modifiedContact = before.iterator().next();
  ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("1").withLastname("2").withAddress("3")
                         .withHomephone("4").withMobilephone("5").withWorkphone("6").withEmail1("7").withEmail2("8").withEmail3("9");
  app.contact().modify(contact);
//  Contacts after = app.contact().all();
    Contacts after=app.db().contacts();
  assertEquals(after.size(),before.size());
  assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
}
}
