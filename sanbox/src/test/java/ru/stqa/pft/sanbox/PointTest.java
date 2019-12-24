package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
  @Test
  public void TestPoint() {
    Point p1=new Point(5,3);
    Point p2=new Point(0,9);
    Assert.assertEquals(p1.distance(p2),8.0);
    Point p3=new Point(0,0);
    Point p4=new Point(0,0);
    Assert.assertEquals(p3.distance(p4),0.0);
    Point p5=new Point(-5,1);
    Point p6=new Point(9,-8);
    Assert.assertEquals(p5.distance(p6),17.0);
  }
}
