package ru.stqa.pft.addressbook.genegators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names="-c", description="Contact count")
  public int count;
  @Parameter (names="-f", description="Target file")
  public String file;
  @Parameter (names="-d", description="Date format")
  public String format;

  public static void main(String[] args) throws IOException {//если будет исключеник, программа остановится
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts= generateContacts(count);
    if(format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    } else if(format.equals("xml"))
    {
      saveAsXml(contacts,new File(file));
    } else{
      System.out.println("Unregnized format "+format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream=new XStream();
    xStream.processAnnotations(ContactData.class);
    xStream.alias("contact",ContactData.class);
    String xml=xStream.toXML(contacts);
    try ( Writer writer=new FileWriter(file)){
      writer.write(xml);
    }
   }

  private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {// если возникнет исключение - выбросить его в main
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer=new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%\n", contact.getName(), contact.getLastname(), contact.getAddress(), contact.getEmail1()
                , contact.getEmail2(), contact.getEmail3(), contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone()));
      }
    }

  }
  private static List<ContactData> generateContacts (int count) {
    List<ContactData> contacts= new ArrayList<ContactData>();
    for (int i=0; i<count; i++){

      contacts.add(new ContactData().withName(String.format("Name%s",i)).withLastname(String.format("LastName%s",i))
              .withAddress(String.format("Address%s",i)).withEmail1(String.format("email1_%s@bk.ru",i)).withEmail2(String.format("email2_%s@bk.ru",i))
              .withEmail3(String.format("email3_%s@bk.ru",i)).withHomephone(String.format("11111-%s",i)).withMobilephone(String.format("22222-%s",i))
              .withWorkphone(String.format("33333-%s",i)));
    }
    return contacts;
  }

}
