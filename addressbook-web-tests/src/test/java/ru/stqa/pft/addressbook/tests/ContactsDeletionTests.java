package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactsDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions (){
    //если не сущ ни одного контакта сначала создать его
    if (app.contact().list().size()==0){
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      app.checkAndCreateGroup(new GroupData().withName("test1"));
      app.contact().create(new ContactData().withName("Саша").withLastname("Иванов")
              .withAddress("г. Москва ул.Строителей д.7 кв 9").withHomephone("1111111111111").withMobilephone("22222222222")
              .withEmail("1111@11.com").withGroup("test1"),true);
    }
  }
  @Test
  public void testContactsDeletion(){
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
   // int index=before.size()-1;
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size()-1);
    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
