package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    //если не сущ ни одной группы, то создать новую
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()-1);//сравниваются размеры списка до и после удаления

    before.remove(before.size()-1);//Удаляем ненужный элемент элемент. старый список должен содержать
                                         // столько же элементов, что и новый
    //проверяем соответствие элементов (два списка имеют одинаковый набор элементов)
    Assert.assertEquals(before, after);//сравниваем 2 списка

  }


}
