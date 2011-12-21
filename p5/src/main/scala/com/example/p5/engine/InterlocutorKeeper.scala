package com.example.p5.engine

import org.squeryl._;
import org.squeryl.dsl._;
import org.squeryl.PrimitiveTypeMode._;

import com.example.p5.persistence.TalkDBSchema._;

/**
 * talk agent which keep interlocutor
 */
trait InterlocutorKeeper {

	
	this : TalkAgent =>
	
 // var interlocutorName:Option[String]
  
    def interlocutorName:Option[String] =
    	inTransaction {
    	   from(userProfiles)(u=>where(u.id===name) select(u) ) headOption match {
    	  	 case Some(u) => u.lastInterlocutor
    	  	 case None   => None
    	   }
        }
	
    def interlocutorName_=(x:Option[String]):Unit =
    {
    	inTransaction {
    		update(userProfiles)(u=>where(u.id===name) set(u.lastInterlocutor:=x) ); 
    	}
    }
	
}
