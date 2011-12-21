package com.example.p5.persistence

import org.squeryl._
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