package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
   @BeforeMethod
   public void ensurePreconditions (){
     app.getNavigationHelper().gotoGroupPage();
     //если не сущ ни одной группы, то создать новую
     if (!app.getGroupHelper().isThereAGroup()){
       app.getGroupHelper().createGroup(new GroupData("test1", null, null));
     }
   }


  @Test
  public void testGroupModification(){
    List<GroupData> before=app.getGroupHelper().getGroupList();
    int index=before.size()-1;
    GroupData group = new GroupData(before.get(index).getId(),"test1", "test2", "test3");
    app.getGroupHelper(). modifyGroup(index, group);
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());
    before.remove(index);//удаляем
    before.add(group);//добавляем
    Comparator<? super GroupData> byID= (g1,g2)->Integer.compare(g1.getId(),g2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before,after);
  }



}
