package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{
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
  public void testContactEmail() {
    app.contact().gotohome();
    ContactData contact = app.contact().all().iterator().next();
    //проверка, что телефоны из главной странице, попадут в форму модификации
    ContactData contactInfoFromEditForm=app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s)->!s.equals("")).collect(Collectors.joining("\n"));
  }
 /*public static String cleaned (String email){
    return email.replaceAll("\\s","").replaceAll("[@._]",""); //очищаем строку от мусора
  }*/
}
