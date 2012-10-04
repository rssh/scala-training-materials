package com.example.p4.engine

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

trait LogbackLogged extends Logged {

	def log(message:String):Unit = logger.warn("lb:"+message);
	
	def logger: Logger;
}

