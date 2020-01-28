package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
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
    //при модификации указываем новое имя, геттер, футтер, а id будет старый
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"test1", "test2", "test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();

    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());
    before.remove(before.size()-1);//удаляем
    before.add(group);//добавляем
    Comparator<? super GroupData> byID= (g1,g2)->Integer.compare(g1.getId(),g2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before,after);
  }

}
