package com.example.p1


class FunctionParamsExample {

  
  def byValue(x: String, nCalls: Int)
  {
    val sb = new StringBuilder();
    for(i <- 1 to nCalls) {
       sb.append(x);
    }
    Console.println(sb.toString);
  }
  
  def byName(x: => String, nCalls: Int ) 
  {
    val sb = new StringBuilder();
    for(i <- 1 to nCalls) {
       sb.append(x);
    }
    Console.println(sb.toString);    
  }
  
  def byNameLazy(x: => String, nCalls: Int) 
  {
    lazy val lx = x;
    val sb = new StringBuilder();
    for(i <- 1 to nCalls) {
       sb.append(lx);
    }
    Console.println(sb.toString);    
  }  
  
  def funByValue(x: ()=>String, nCalls: Int )
  {
    val sb = new StringBuilder();
    for(i <- 1 to nCalls) {
       sb.append(x());
    }
    Console.println(sb.toString);        
  }
  
}