package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactsModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions (){
    //если не сущ ни одного контакта сначала создать его
    if (!app.getContactHelper().isTereAContact()){
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      app.checkAndCreateGroup(new GroupData("test1", null, null));
      app.getContactHelper().createContact(new ContactData("Саша", "Иванов",
              "г. Москва ул.Строителей д.7 кв 9", "1111111111111", "22222222222",
              "1111@11.com","test1" ),true);
    }
  }
  @Test
public void testContactsModification(){
  List<ContactData> before = app.getContactHelper().getContactList();
  int index=before.size()-1;
  ContactData contact = new ContactData(before.get(index).getId(),"1","2","3","4",
            "5","6",null);
  app.getContactHelper().modifyContact(index, contact);
  List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(),before.size());
  before.remove(index);
  before.add(contact);
  Comparator<? super ContactData> byId=(g1,g2)-> Integer.compare(g1.getId(),g2.getId());
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
}
}
