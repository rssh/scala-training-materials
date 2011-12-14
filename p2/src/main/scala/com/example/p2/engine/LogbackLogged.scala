package com.example.p2.engine

package com.example.p2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

trait LogbackLogged {

	def log(message:String):Unit = logger.info(message);
	
	def logger: Logger;
}

