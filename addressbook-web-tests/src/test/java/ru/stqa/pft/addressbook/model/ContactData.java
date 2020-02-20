package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id;

  @Column(name="firstname")
  private  String name;

  @Column(name="lastname")
  private String lastname;

  @Column(name="address")
  @Type(type="text")
  private  String address;

  @Column(name="home")
  @Type(type="text")
  private  String homephone;

  @Column(name="mobile")
  @Type(type="text")
  private String mobilephone;

  @Column(name="work")
  @Type(type="text")
  private String workphone;

  @Column(name="email")
  @Type(type="text")
  private  String email1;

  @Column(name="email2")
  @Type(type="text")
  private  String email2;

  @Column(name="email3")
  @Type(type="text")
  private  String email3;

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Transient
  @Column(name="photo")
  @Type(type="text")
  private String photo;

  @ManyToMany(fetch=FetchType.EAGER)
  @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name="group_id"))
  //private Set<GroupData> groups=new HashSet<GroupData>();
  private Set<GroupData> groups;

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }
  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }


  public int getId() {
    return id;
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

  public String getWorkphone() {
    return workphone;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public File getPhoto() {
    return new File (photo);
  }
  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }
  //вытягивание в цепочку
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }
  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public Groups getGroups() {
    return new Groups(getGroupSet());//множество превратить в объект типа Groups при этом создается копия
  }
  public ContactData inGroup(GroupData group){
    groups.add(group);
    return this;
  }
  //чтобы не возникал NullPointerExeption, когда множество groups null
  //производим проверку условия и присваиваем и в этом случае groups = new HashSet<>()
  protected Set<GroupData> getGroupSet() {
    if (groups == null) {
      groups = new HashSet<>();
    }
    return groups;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(homephone, that.homephone) &&
            Objects.equals(mobilephone, that.mobilephone) &&
            Objects.equals(workphone, that.workphone) &&
            Objects.equals(email1, that.email1) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, address, homephone, mobilephone, workphone, email1, email2, email3);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", homephone='" + homephone + '\'' +
            ", mobilephone='" + mobilephone + '\'' +
            ", workphone='" + workphone + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", allEmails='" + allEmails + '\'' +
           '}';
  }

}
