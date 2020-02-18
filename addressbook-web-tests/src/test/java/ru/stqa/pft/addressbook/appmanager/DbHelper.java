package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;
  private final SessionFactory sessionFactory1;

  public DbHelper () {
    final StandardServiceRegistry registry= new StandardServiceRegistryBuilder().configure().build();
    final StandardServiceRegistry registry1= new StandardServiceRegistryBuilder().configure().build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    sessionFactory1 = new MetadataSources(registry1).buildMetadata().buildSessionFactory();
    }
    public Groups groups() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery("from GroupData").list();
      session.getTransaction().commit();
      session.close();
      return new Groups(result);
     }
    public Contacts contacts(){
      Session session = sessionFactory1.openSession();
      session.beginTransaction();
      List<ContactData> result = session.createQuery("from ContactData ").list();
      session.getTransaction().commit();
      session.close();
      return new Contacts(result);
    }

}