package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactsDeletionTests extends TestBase {
  @Test
  public void testContactsDeletion(){
    //если не сущ ни одного контакта сначала создать его
    if (!app.getContactHelper().isTereAContact()){
    //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
         app.getNavigationHelper().gotoGroupPage();
         if (!app.getGroupHelper().isThereAGroup()){
           app.getGroupHelper().createGroup(new GroupData("test1", null, null));
         }
         app.getContactHelper().gotohome();
          app.getContactHelper().createContact(new ContactData("Саша", "Иванов",
              "г. Москва ул.Строителей д.7 кв 9", "1111111111111", "22222222222",
              "1111@11.com","test1" ),true);
    }

   app.getContactHelper().selectContacts();
   app.getContactHelper().deleteSelectedContacts();
   app.getContactHelper().assertTrueDeletionContacts();
  }
}
