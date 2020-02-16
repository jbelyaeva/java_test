package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.sql.*;

public class dbConnectionTest {
  @Test
  public void testdbConnection(){
    Connection conn=null;
    try{
      conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
      Statement st1=conn.createStatement();
      Statement st2=conn.createStatement();

      ResultSet rsGroup = st1.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
      ResultSet rsContact = st2.executeQuery("select id, firstname, lastname, address from addressbook");
      Groups groups=new Groups();
      Contacts contacts=new Contacts();
      while(rsGroup.next()){
        groups.add(new GroupData().withId(rsGroup.getInt("group_id")).withName((rsGroup.getString("group_name")))
                        .withHeader((rsGroup.getString("group_header")))
                        .withFooter((rsGroup.getString("group_footer"))));
      }
      while(rsContact.next()){
        contacts.add(new ContactData().withId(rsContact.getInt("id")).withName((rsContact.getString("firstname")))
                .withLastname((rsContact.getString("lastname")))
                .withAddress((rsContact.getString("address"))));
      }
      rsGroup.close();
      rsContact.close();
      st1.close();
      st2.close();
      conn.close();
      System.out.println(groups);
      System.out.println(contacts);
    } catch (SQLException ex) {
      System.out.println("SQLExeption:"+ex.getMessage());
      System.out.println("SQLState:"+ex.getSQLState());
      System.out.println("VendorError:"+ex.getErrorCode());
    //  ex.printStackTrace();
    }
  }
}
