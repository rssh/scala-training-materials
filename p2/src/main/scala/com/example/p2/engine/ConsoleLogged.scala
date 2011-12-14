package com.example.p2.engine

trait ConsoleLogged extends Logged {

  def log(message:String) = Console.println(message);
  
}