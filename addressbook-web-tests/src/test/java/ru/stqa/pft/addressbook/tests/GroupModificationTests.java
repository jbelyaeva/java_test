package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification(){
     app.getNavigationHelper().gotoGroupPage();
    //если не сущ ни одной группы, то создать новую
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  //  int before =app.getGroupHelper().getGroupCount();
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
   // int after =app.getGroupHelper().getGroupCount();
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after,before);
  }

}
