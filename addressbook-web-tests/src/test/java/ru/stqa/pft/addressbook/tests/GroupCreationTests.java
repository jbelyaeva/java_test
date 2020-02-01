package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation()  {
    app.goTo().groupPage();
    List<GroupData> before=app.group().list();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    List<GroupData> after=app.group().list();
    Assert.assertEquals(after.size(),before.size()+1);//сравниваем размеры списка
    //далее сравнить страный список с новым без учета порядка
    //сравниваем 2 объекта типа groupData при помощи кмпаратора, находя макс элемент
    group.withId(after.stream().max ((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byID= (g1,g2)->Integer.compare(g1.getId(),g2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before,after);
     }
}
