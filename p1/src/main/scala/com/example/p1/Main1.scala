package com.example.p1;

object Main1 extends DelayedInit
{

  Console.println("Hello, world");

  def delayedInit(x: => Unit) {
    Console.println("receive x");
    fun=Some(()=>x);
  }


  def main(args:Array[String]):Unit =
  {
    Console.println("main");
    fun.map(_.apply);
  }

  var fun:Option[Function0[Unit]] = None;

}
