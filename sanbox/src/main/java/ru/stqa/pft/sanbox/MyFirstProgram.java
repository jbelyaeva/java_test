package ru.stqa.pft.sanbox;

public class MyFirstProgram {

     public static void main(String[] args) {
          System.out.println("Расстояние между двумя точками равно:");

          Point p= new Point(5,2,7,1);
          System.out.println("Р1("+p.x1+";"+p.y1+")   P2("+p.x2+";"+p.y2+")   L="+ p.distance());
          Point p1= new Point(8,6,0,9);
          System.out.println("Р1("+p1.x1+";"+p1.y1+")   P2("+p1.x2+";"+p1.y2+")   L="+ p1.distance());
          Point p2= new Point(5,9,9,1);
          System.out.println("Р1("+p2.x1+";"+p2.y1+")   P2("+p2.x2+";"+p2.y2+")   L="+ p2.distance());

     }

}


