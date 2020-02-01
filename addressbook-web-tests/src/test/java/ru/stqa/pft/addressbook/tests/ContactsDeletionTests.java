package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactsDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions (){
    //если не сущ ни одного контакта сначала создать его
    if (app.contact().list().size()==0){
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      app.checkAndCreateGroup(new GroupData("test1", null, null));
      app.contact().create(new ContactData("Саша", "Иванов",
              "г. Москва ул.Строителей д.7 кв 9", "1111111111111", "22222222222",
              "1111@11.com","test1" ),true);
    }
  }
  @Test
  public void testContactsDeletion(){
    List<ContactData> before = app.contact().list();
    int index=before.size()-1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size()-1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
