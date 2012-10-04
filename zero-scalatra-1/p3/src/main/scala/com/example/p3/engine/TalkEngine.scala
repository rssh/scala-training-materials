package com.example.p3.engine

import org.slf4j._;
import scala.collection.mutable.ConcurrentMap
import java.util.concurrent.{ ConcurrentHashMap => JavaConcurrentHashMap }
import scala.collection.JavaConversions._;

import agents._;

object TalkEngine extends TalkAgentRegistryProvider
                       with LoggingMessageProcessor 
                       with LogbackLogged
{

     lazy val registry: TalkAgentRegistry = 
     {
      val retval = new SimpleTalkAgentRegistry(Util.newConcurrentHashMap[String,TalkAgent]);
      retval.add(Elize)
      retval.add(YesSir);
      retval.add(SysAgent)
      retval.add(new AllMessagesAgent(retval))
      log("TalkEngine:Initialization finished");
      retval;
     }
     
     override val logger = LoggerFactory.getLogger(this.getClass);
}
