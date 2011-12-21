package com.example.p5.engine.agents

import com.example.p5.engine._;
import com.example.p5.persistence._;
import com.example.p5.persistence.TalkDBSchema._;

import org.squeryl._;
import org.squeryl.dsl._;
import org.squeryl.PrimitiveTypeMode._;



trait DBMessageQueue {

	this: TalkAgent =>
		
   def  giveMessageFor(receiverName: String): Option[String] =
    {
      val messages = popMessagesToShow(receiverName)
      if (messages.isEmpty) None else Some(messages mkString "\n")
    }
  
    def pushMessageToShow(whom: String, message: String):Unit =
    {
        val now = new java.sql.Timestamp(System.currentTimeMillis());
    	inTransaction {
    		val tm = new TalkMessage(id=0L, xfrom = name, xto = whom, message = message, when=now)
    		val m = messages.insert(tm);
    	}
    }

    def popMessagesToShow(whom: String): List[String] =
    {
      val now = new java.sql.Timestamp(System.currentTimeMillis()); 	
      inTransaction {
      	val retval = from(messages,userProfiles)((m,u)=>where((m.xfrom===name)
      			                                 and
      			                                 (m.xto===whom)
      			                                 and
      			                                  (u.id===m.xto)
      			                                 and
      			                                  (m.when > u.lastUpdate)
      	                                         ) select(m.message)
      			                   ).toList
      	update(userProfiles)( u => where(u.id === whom)
         		                   set(u.lastUpdate := now)
         		            )       
      	retval;		                   
      }
      
    }
    
     			
}