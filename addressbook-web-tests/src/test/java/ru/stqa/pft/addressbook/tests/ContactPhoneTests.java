package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions (){
    //если не сущ ни одного контакта сначала создать его
    if (app.db().contacts().size()==0){
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      Groups groups=app.db().groups();
      String group = app.contact().getGroupForContactCreation().iterator().next().getName();
      app.contact().create(new ContactData().withName("Саша").withLastname("Сиванов")
              .withAddress("г. Москва ул.Строителей д.7 кв 9").withHomephone("1111111111111").withMobilephone("22222222222")
              .withEmail1("1111@11.com").withEmail2("222@gh.com").withEmail3("ghj@gh.ru").inGroup(groups.iterator().next()),true);
    }
  }

  @Test
  public void testContactPhones() {
    app.contact().gotohome();
    ContactData contact = app.contact().all().iterator().next();//контакт, загруженный с главной страницы
    //проверка, что телефоны из главной странице, попадут в форму модификации
    ContactData contactInfoFromEditForm=app.contact().infoFromEditForm(contact);//загружен с формы редактирования
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }
//склеиваем в строку. Одна строчка фильтрует и склеивает
  private String mergePhones(ContactData contact) {
   return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
            .stream().filter((s)->!s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }

 public static String cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[()-]",""); //очищаем строку от мусора
  }
}
