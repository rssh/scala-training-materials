package com.example.p4.engine.agents

import akka.actor._;
import scala.concurrent.stm._;
import org.slf4j._;
import com.example.p4.engine._;


class AlarmActor extends Actor {
  
  def receive = {
    case ClockMessage(resolution) => checkAlarms
    case AlarmMessage(millis, what, whom) => atomic { implicit txn =>
                                                val r = AlarmRecord(millis,what);
                                                alarms.get(whom) match {
                                                  case Some(records) => alarms.put(whom,r::records);
                                                  case None => alarms.put(whom,r::Nil)
                                                }
                                             }
    case x => logger.warn("received unknown message: {}",x.toString);
  }

  def checkAlarms = {
     val now = System.currentTimeMillis();
    // TODO:  rewrite with view.
     atomic { implicit txn =>
       for( (name, records) <- alarms)  {
         val newRecords = records.flatMap( r =>
           if (r.when >= now) {
               //  -- bad, in atomic prefer not use external blocking
               TalkEngine.processSend("alarm",name,r.message);
               None
           } else {
               Some(r)
           }
         );
         alarms.put(name, newRecords);
       }
     }
    // alarms.put("1",AlarmRecord(0L,"11"))
  }
    
  val alarms = TMap[String,List[AlarmRecord]]();
  
  val logger = LoggerFactory.getLogger(classOf[AlarmActor]);
}