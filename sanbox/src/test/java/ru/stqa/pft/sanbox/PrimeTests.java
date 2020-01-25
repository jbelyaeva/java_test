package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
  @Test
  public void testPreme(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }
  @Test (enabled = false)
  public void testPremeLong(){
    long n=Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
  @Test
  public void testNonPreme(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }
}
