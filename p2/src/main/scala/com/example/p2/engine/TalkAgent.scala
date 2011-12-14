package com.example.p2.engine



/**
 * Base trait for possible interlocutors.
 **/
trait TalkAgent {
  
  
  def  name:String;

  def  answer(askingName: String, message:String) :String;
  
  def  isHuman: Boolean = false;
  
  def  addSentence(message:String): Unit = throw new UnsupportedOperationException("unsupported");
  
}