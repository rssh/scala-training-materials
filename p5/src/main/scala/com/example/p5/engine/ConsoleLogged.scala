package com.example.p5.engine

trait ConsoleLogged extends Logged {

  def log(message:String) = Console.println(message);
  
}
