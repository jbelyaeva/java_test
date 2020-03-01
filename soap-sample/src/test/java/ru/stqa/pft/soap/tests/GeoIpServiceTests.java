package ru.stqa.pft.soap.tests;

import net.webservicex.GeoIP;
import org.testng.Assert;
import org.testng.annotations.Test;
import net.webservicex.GeoIPService;

public class GeoIpServiceTests {
  @Test
  public void testMyIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("185.54.21.152");
    Assert.assertEquals(geoIP.getCountryCode(),"RUS");
  }
}
