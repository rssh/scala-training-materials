package com.example.p5.engine

import org.slf4j._;
import scala.collection.mutable.ConcurrentMap
import java.util.concurrent.{ ConcurrentHashMap => JavaConcurrentHashMap }
import scala.collection.JavaConversions._;

import agents._;

class TalkEngine
{
	type NameType = String;	
}

object TalkEngine extends TalkEngine
    with TalkAgentRegistryProvider
	with LoggingMessageProcessor
	with LogbackLogged {
	
	

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

	def dispatch(login: NameType, message: String): String =
		{
			TalkEngine.registry.find(login) match {
				case Some(me) =>
					if (me.isInstanceOf[InterlocutorKeeper]) {
						val ilme = me.asInstanceOf[InterlocutorKeeper]
						if (ilme.interlocutorName.isEmpty) {
							TalkEngine.chooseAgent(login, message) match {
								case Left(x) => x
								case Right(x) =>
									ilme.interlocutorName = Some(x)
									"You connected to " + x
							}
						} else {
							if (!message.isEmpty()) {
								val receiver = if (message.startsWith(":")) SysAgent.NAME else ilme.interlocutorName.get;
								TalkEngine.processSend(login, receiver, message);
							}
							TalkEngine.processRequestNew(login)
						}
					} else {
						"login is not intedent to use from this interface"
					}
				case None => "login not found in registry, reconnect please"
			}
		}

	override val logger = LoggerFactory.getLogger(this.getClass);
}
