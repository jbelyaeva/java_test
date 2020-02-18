package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class GroupModificationTests extends TestBase {
   @BeforeMethod
   public void ensurePreconditions () {
     if (app.db().groups().size() == 0) {
       //если не сущ ни одной группы, то создать новую
       if (app.group().all().size() == 0) {
         app.group().create(new GroupData().withName("test1"));
       }
     }
   }
  @Test
  public void testGroupModification(){
    Groups before=app.db().groups();
    GroupData modifiedGroup=before.iterator().next();
    int index=before.size()-1;
    GroupData group = new GroupData()
                      .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));//хеширование
    Groups after=app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }

}
