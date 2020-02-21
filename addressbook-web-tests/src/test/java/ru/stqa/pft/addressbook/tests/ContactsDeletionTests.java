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

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactsDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions (){
    //если не сущ ни одного контакта сначала создать его
    if (app.db().contacts().size()==0){
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      String group = app.contact().getGroupForContactCreation().iterator().next().getName();
      ContactData contact = new ContactData().withName("Semenov").withLastname("Sem");
      Groups groups = app.db().groups();
      app.contact().create(contact.inGroup(groups.iterator().next()), true);
    }
  }
  @Test
  public void testContactsDeletion(){

    Contacts before=app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size()-1));
    Contacts after=app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }


}
