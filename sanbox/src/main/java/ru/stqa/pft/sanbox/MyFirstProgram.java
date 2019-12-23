package ru.stqa.pft.sanbox;

public class MyFirstProgram {

     public static void main(String[] args) {
          Point p1=new Point(5,7);
          Point p2=new Point(8,6);
          System.out.println("Расстояние между двумя точками А("+p1.x+","+p1.y+") и В("+p2.x+","+p2.y+") равно "+p1.distance(p2));
          Point p3=new Point(-1,7);
          Point p4=new Point(8,-6);
          System.out.println("Расстояние между двумя точками А("+p3.x+","+p3.y+") и В("+p4.x+","+p4.y+") равно "+p3.distance(p4));
          Point p5=new Point(0,0);
          Point p6=new Point(8,-6);
          System.out.println("Расстояние между двумя точками А("+p5.x+","+p5.y+") и В("+p6.x+","+p6.y+") равно "+p5.distance(p6));

     }
}


