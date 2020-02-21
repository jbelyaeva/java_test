package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTests extends TestBase {

  private ContactData targetContact;
  private GroupData targetGroup;

  @BeforeMethod
  public void ensurePreconditions() {
    //если не сущ ни одного контакта сначала создать его
    if (app.db().contacts().size() == 0) {
      //если не сущ ни одной группы,то создать группу, чтобы в последствии выбрать ее из выпадающего списка при создании контакта
      String group = app.contact().getGroupForContactCreation().iterator().next().getName();
      ContactData contact = new ContactData().withName("Semenov").withLastname("Sem");
      Groups groups = app.db().groups();
      app.contact().create(contact.inGroup(groups.iterator().next()), true);
    }

  Contacts contactSet = app.db().contacts();// берем контакты из базы
  Groups groupSet = app.db().groups();// берем группы из базы
  //По всем контактам
    for ( ContactData contact :contactSet) //для каждого контакта перебираем все группы
     { //По всем группам
          for (GroupData group : groupSet) {
              //Если группа с таким названием повторяется хотя бы дважды,
                  if ( groupSet.stream().filter(g -> g.getName().equals(group.getName())).collect(Collectors.toList()).size() > 1){
                  //то она нам не подходит
                  continue;
                  }
                   //Если в такую группу контакт входит,
                  if (contact.getGroups().contains(group)) {
                    //эта пара нам подходит
                    targetContact = contact;
                    targetGroup = group;
                    break;
            }
          }
     }

    if ((targetContact==null)&&(targetGroup==null)) {
      //Не удалось найти подходящую пару, поэтому создаём сами.
      Groups groups = app.db().groups();
      Contacts contacts = app.db().contacts();
      targetContact=contacts.iterator().next();
      targetGroup=groups.iterator().next();
      app.contact().selectContactsById(targetContact.getId());
      app.contact().contactAddToGroup(targetContact ,targetGroup);
    }

  //Перезапросить свежие данные, чтобы узнать id добавленных контакта и группы
  contactSet =app.db().contacts();
  groupSet =app.db().groups();
  targetContact.withId(contactSet.stream().mapToInt((c) ->c.getId()).max().getAsInt());
  targetGroup.withId(groupSet.stream().mapToInt((g) ->g.getId()).max().getAsInt());
}

@Test
 public void testContactDeleteFromGroup() {
  app.contact().contactDeleteFromFroup(targetContact,targetGroup);
  ContactData contactFromDb = app.db().getContact(targetContact.getId());
  assertThat(contactFromDb.getGroups(), not(hasItem(targetGroup)));
}

}