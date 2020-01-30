package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String name;
  private final String lastname;
  private final String address;
  private final String homephone;
  private final String mobilephone;
  private final String email;
  private String group;


    public String getId() {
    return id;
  }

  public ContactData(String id, String name, String lastname, String address, String homephone, String mobilephone, String email,
                     String group) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.email = email;
    this.group = group;
  }
  public ContactData( String name, String lastname, String address, String homephone, String mobilephone, String email,
                     String group) {
    this.id = null;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.email = email;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname);
  }

}
