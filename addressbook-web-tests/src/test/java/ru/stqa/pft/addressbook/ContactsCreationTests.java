package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactsCreation() throws Exception {

    gotoAddNewContact();
    fillContactForm(new ContactData("Саша", "Иванов", "г. Москва ул.Строителей д.7 кв 9", "1111111111111", "22222222222", "1111111@11.com"));
    submitContactCreation();
     }


}
