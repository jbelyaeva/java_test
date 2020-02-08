package ru.stqa.pft.addressbook.genegators;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  public static void main(String[] args) throws IOException {//если будет исключеник, программа остановится
    int count= Integer.parseInt(args[0]);
    File file =new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts,file);
  }
  private static void save(List<ContactData> contacts, File file) throws IOException {// если возникнет исключение - выбросить его в main
    System.out.println(new File(".").getAbsolutePath());
    Writer writer=new FileWriter(file);//открываем файл на запись
    for(ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;\n",contact.getName(), contact.getLastname(), contact.getAddress(), contact.getEmail1()
              ,contact.getEmail2(),contact.getEmail3(), contact.getHomephone(), contact.getMobilephone(),contact.getWorkphone()));
    }
    writer.close();//закрыть файл
  }
  private static List<ContactData> generateContacts (int count) {
    List<ContactData> contacts= new ArrayList<ContactData>();
    for (int i=0; i<count; i++){
      contacts.add(new ContactData().withName(String.format("Имя %s",i)).withLastname(String.format("Фамилия %s",i))
              .withAddress(String.format("Адрес %s",i)).withEmail1(String.format("email1 %s",i)).withEmail2(String.format("email2 %s",i))
              .withEmail3(String.format("email3 %s",i)).withHomephone(String.format("11111- %s",i)).withMobilephone(String.format("22222- %s",i))
              .withWorkphone(String.format("33333- %s",i)));
    }
    return contacts;
  }

}
