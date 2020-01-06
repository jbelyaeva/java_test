package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsModificationTests extends TestBase {
@Test
public void testContactsModification(){
  app.getContactHelper().submitContactModification();
  app.getContactHelper().fillContactForm(new ContactData("1","2","3","4",
          "5","6",null),false);
  app.getContactHelper().submitContactUpdate();
}
}
