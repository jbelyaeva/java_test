package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactsModificationTests extends TestBase {
@Test
public void testContactsModification(){

  if (!app.getGroupHelper().isThereAGroup()){
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
  }
  app.getContactHelper().submitContactModification();
  app.getContactHelper().fillContactForm(new ContactData("1","2","3","4",
          "5","6",null),false);
  app.getContactHelper().submitContactUpdate();
}
}
