package com.example.p4.engine



/**
 * Base trait for possible interlocutors.
 **/
trait TalkAgent {
  
  def  name:String;

  def  takeMessage(senderName: String, message: String): Unit;
  
  def  giveMessageFor(receiverName: String): Option[String];
  
  def  isParticipateInAll: Boolean = false;
  
  def  recordOwnMessage(to:String, message:String) ;
  
}
