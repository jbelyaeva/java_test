package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation()  {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before=app.getGroupHelper().getGroupList();
    //int before =app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test2", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after=app.getGroupHelper().getGroupList();
    //int after =app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(),before.size()+1);//сравниваем размеры списка


    //далее сравнить страный список с новым без учета порядка
    //сравниваем 2 объекта типа groupData при помощи кмпаратора, находя макс элемент
    group.setId(after.stream().max ((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byID= (g1,g2)->Integer.compare(g1.getId(),g2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before,after);
     }
}
