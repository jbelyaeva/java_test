package ru.stqa.pft.sanbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){
    String[] lengs = {"Java","C#","Python","PHP"};//массив
    List<String> languages= Arrays.asList("Java","C#","Python","PHP"); //список

    for (String l:languages) {
      System.out.println("Я хочу выучить "+l);
    }
  }
}
