package com.example.p5.persistence

import org.squeryl._
import org.squeryl.dsl._
import org.squeryl.PrimitiveTypeMode._
import java.sql.Timestamp

class UserProfile(
	 val id: String,
	 val password: Option[String],
	 val info: Option[String],
	 val lastInterlocutor: Option[String],
	 val lastUpdate: Timestamp
	 ) extends KeyedEntity[String]
	 {
	
	//def id = login; 
	
	def this() = this("",Some(""),Some(""),Some(""),new Timestamp(0L));
	
	lazy val sendMessages = TalkDBSchema.fromUsersToMessages.left(this);
	
	lazy val receivedMessages = TalkDBSchema.fromUsersToMessages.left(this);
	
}

object UserProfile
{
  import TalkDBSchema._;
  
  def checkLogin(login:String, password:String):Boolean =
  {
    inTransaction {
    	from(userProfiles)(u => where(u.id===login)select(u)).headOption match {
    		case Some(u) => u.password == password
    		case None => 
    			if (password == passwordForNewUsers()) {
    				// create login.
    				val now = new Timestamp(System.currentTimeMillis());
    				userProfiles.insert(new UserProfile(login,None,None,None,now));
    				true;
    			} else {
    				false
    			}
    	}
    }	
  }
  
  def passwordForNewUsers():String = 
  {
  	val propertyName="defpasswd"
  	inTransaction {
  		from(properties)(p => where (p.name===propertyName)select(p)).headOption match {
  			case Some(x) => x.value
  			case None => val retval="qqq";
  			             properties insert (Property(propertyName, retval));
  			             retval
  		}
  	}
  }
  
}