package com.example.p1

class C[+A] extends A1 with B1 
{
  
  def x:Unit = {
    myMethod();
    myMethod();
  }

}

object C
{

   def  s:Int = 3;
   
   private val x = 3;
   private[p1] val y = 3; 
   
   //def  (c1: C, c3: C):Unit 

}

