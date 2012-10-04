package com.example.p3.engine

trait ConsoleLogged extends Logged {

  def log(message:String) = Console.println(message);
  
}
