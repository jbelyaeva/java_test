package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactsDeletionTests extends TestBase {
  @Test
  public void testContactsDeletion(){
    //если не сущ ни одного контакта сначала создать его
    if (!app.getContactHelper().isTereAContact()){
    //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      app.checkAndCreateGroup(new GroupData("test1", null, null));
      app.getContactHelper().createContact(new ContactData("Саша", "Иванов",
              "г. Москва ул.Строителей д.7 кв 9", "1111111111111", "22222222222",
              "1111@11.com","test1" ),true);
    }

    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContacts(before-1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().assertTrueDeletionContacts();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before-1);
  }
}
