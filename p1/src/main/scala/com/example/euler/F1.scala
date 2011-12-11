package com.example.euler

object F1 extends (()=>Int)
{

  //If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
  //Find the sum of all the multiples of 3 or 5 below 1000.
  
  def apply() =
    (1 to 1000) filter ( (x:Int) => x%3==0  || x%5==0 ) sum
  
}