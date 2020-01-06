package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactsCreation() throws Exception {

    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Саша", "Иванов",
            "г. Москва ул.Строителей д.7 кв 9", "1111111111111", "22222222222",
            "1111111@11.com","test1" ),true);
    app.getContactHelper().submitContactCreation();
     }

}
