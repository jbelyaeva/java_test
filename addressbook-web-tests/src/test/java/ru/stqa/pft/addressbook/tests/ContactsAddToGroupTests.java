package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsAddToGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    //если не сущ ни одного контакта сначала создать его
    if (app.db().contacts().size() == 0) {
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      Groups groups = app.db().groups();
      String group = app.contact().getGroupForContactCreation().iterator().next().getName();
      app.contact().create(new ContactData().withName("Саша").withLastname("Сиванов")
              .withAddress("г. Москва ул.Строителей д.7 кв 9").withHomephone("1111111111111").withMobilephone("22222222222")
              .withEmail1("1111@11.com").withEmail2("222@gh.com").withEmail3("ghj@gh.ru").inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testAddToGroup() {
   Groups groups=app.db().groups();//взяли данные из таблицы гроуп
    Contacts before=app.db().contacts();//взяли данные из таблицы с контактами
    ContactData addToGroupContact = before.iterator().next();//первый контакт
    GroupData groupForAdd=groups.iterator().next();//первая группа
    app.contact().contactAddToGroup(addToGroupContact, groups);//взяли первый контакт и добавили в первую группу


  }


  }