package com.example.p5.engine.agents

import akka.actor._;

import com.example.p5.engine._;

           
case class AlarmRecord(val when: Long, val message:String);


object AlarmAgent extends TalkAgent 
                              //with OutputQueue
                              with DBMessageQueue
{
  
  def name = "alarm";
  
 def  takeMessage(senderName: String, message: String): Unit =
 { 
   parseAlarmCall(message) match {
     case Right(alarmRecord) => 
            val alarmActor = Actor.registry.actorsFor(classOf[AlarmActor])(0);
            alarmActor ! AlarmMessage(alarmRecord.when,alarmRecord.message, senderName);
     case Left(message) =>
            pushMessageToShow(senderName, message);
   } 
 }  
 
 def  parseAlarmCall(message:String): Either[String,AlarmRecord] =
 {
   val ParseRegexp = "after ([0-9]+) seconds:(.*)".r; 
   message match {
     case ParseRegexp(seconds,message) =>  
        try {
          val nSeconds = Integer.parseInt(seconds)
          Right(AlarmRecord(System.currentTimeMillis+nSeconds*1000, message))
        } catch {
          case ex: NumberFormatException => Left("can't parse number of seconds "+ex.getMessage)
        }
     case _ => Left("Sorry, can't parse alarm expression")   
   }
 }

  def  recordOwnMessage(to:String, message:String) = None;
 
 
}
