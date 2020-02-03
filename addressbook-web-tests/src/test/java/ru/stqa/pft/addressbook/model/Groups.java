package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {
    private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
   }
  public Groups() { //коструктор без параметров
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded (GroupData group){//объекта,в который добавлена группа
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;

  }
  public Groups without (GroupData group){//объекта, из которго удалена группа
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;

  }
}
