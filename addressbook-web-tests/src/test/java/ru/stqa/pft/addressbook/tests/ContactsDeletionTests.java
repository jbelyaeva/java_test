package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactsDeletionTests extends TestBase {
  @Test
  public void testContactsDeletion(){
   app.getContactHelper().selectContacts();
   app.getContactHelper().deleteSelectedContacts();
   app.getContactHelper().assertTrueDeletionContacts();
  }
}
