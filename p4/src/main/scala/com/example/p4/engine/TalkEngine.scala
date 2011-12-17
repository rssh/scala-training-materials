package com.example.p4.engine

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
      val retval = new SimpleTalkAgentRegistry();
      retval.add(Elize)
      retval.add(YesSir);
      retval.add(new SysAgent(retval))
      retval.add(new AllMessagesAgent(retval))
      log("TalkEngine:Initialization finished");
      retval;
     }
     
     override val logger = LoggerFactory.getLogger(this.getClass);
}
