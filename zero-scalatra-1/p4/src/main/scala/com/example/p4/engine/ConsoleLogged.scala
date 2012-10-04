package com.example.p4.engine

trait ConsoleLogged extends Logged {

  def log(message:String) = Console.println(message);
  
}
