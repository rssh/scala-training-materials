package com.example.p2.engine

import org.slf4j._;
import scala.collection.mutable.ConcurrentMap
import java.util.concurrent.{ ConcurrentHashMap => JavaConcurrentHashMap }
import scala.collection.JavaConversions._;

import agents._;

object TalkEngine extends SimpleTalkAgentRegistry(
                             new JavaConcurrentHashMap[String,TalkAgent]()
                          )
                       with LoggingMessageProcessor 
                       with LogbackLogged
{

     lazy val initialized: Boolean =
     {  
      add(Elize);
      add(YesSir);
      add(new AllMessageAgent(this))
      log("TalkEngine:Initialization finished");
      true;
     }
     
     override val logger = LoggerFactory.getLogger(this.getClass);
}