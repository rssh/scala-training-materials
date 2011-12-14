package com.example.p2.engine

/**
 * Base trait for possible interlocutors.
 **/
trait TalkAgent {
  
  def  name:String;

  def  answer(askingName: String, message:String) :String;
  
}