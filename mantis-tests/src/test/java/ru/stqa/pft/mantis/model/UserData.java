package ru.stqa.pft.mantis.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("user")
@Entity
@Table(name="mantis_user_mantis")
public class UserData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id;
  @Column(name="username")
  private String username;
  @Column(name="email")
   private String email;
  @Column(name="password")
  private String password;

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }


}

