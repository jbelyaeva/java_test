package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactsModificationTests extends TestBase {
@Test
public void testContactsModification(){
  //если не сущ ни одного контакта сначала создать его
  if (!app.getContactHelper().isTereAContact()){
    //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
    app.checkAndCreateGroup(new GroupData("test1", null, null));


    app.getContactHelper().createContact(new ContactData("Саша", "Иванов",
            "г. Москва ул.Строителей д.7 кв 9", "1111111111111", "22222222222",
            "1111@11.com","test1" ),true);
  }

  List<ContactData> before = app.getContactHelper().getContactList();
  app.getContactHelper().selectContactModification(before.size()-1);
  ContactData contact = new ContactData(before.get(before.size()-1).getId(),"1","2","3","4",
          "5","6",null);
  app.getContactHelper().fillContactForm(contact,false);
  app.getContactHelper().submitContactUpdate();
  app.getContactHelper().gotohome();
  List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(),before.size());

  before.remove(before.size()-1);
  before.add(contact);
  Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
}
}
