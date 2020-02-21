package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions (){
    //если не сущ ни одного контакта сначала создать его
    if (app.contact().list().size()==0){
      String group = app.contact().getGroupForContactCreation().iterator().next().getName();
      ContactData contact = new ContactData().withName("Semenov").withLastname("Sem");
      Groups groups = app.db().groups();
      app.contact().create(contact.inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactAddress() {
    app.contact().gotohome();
    ContactData contact = app.contact().all().iterator().next();
    //проверка, что телефоны из главной странице, попадут в форму модификации
    ContactData contactInfoFromEditForm=app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
