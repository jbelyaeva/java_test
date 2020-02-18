package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.awt.*;
import java.util.List;

public class HbConnectionTests {
  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception{
    final StandardServiceRegistry registry= new StandardServiceRegistryBuilder().configure().build();
     try{
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e){
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test
  public void testHbConnection(){

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result=session.createQuery("from GroupData").list();
    for (GroupData group:result){
    System.out.println(group);
     }
    Session session1 = sessionFactory.openSession();
    session1.beginTransaction();
    List<ContactData> result1=session1.createQuery("from ContactData where deprecated='0000-00-00'").list();
    for (ContactData contact:result1){
      System.out.println(contact);
    }
  session.getTransaction().commit();
  session1.getTransaction().commit();
  session.close();
  session1.close();
  }
}
