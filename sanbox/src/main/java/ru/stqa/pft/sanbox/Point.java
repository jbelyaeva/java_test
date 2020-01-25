package ru.stqa.pft.sanbox;

public class Point {
  public double x;
  public double y;
  public Point (double x, double y){
    this.x =x;
    this.y =y;}

  public double xCoordinate(){ return x; }
  public double yCoordinate(){ return y; }
  public double distance(Point second){
    double xDistance = x - second.xCoordinate();
    double yDistance = y - second.yCoordinate();
    double l = Math.round(Math.sqrt(xDistance*xDistance+yDistance*yDistance));
    return l;
  }


}
