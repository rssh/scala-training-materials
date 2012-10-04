package com.example.p5.servlet

import javax.servlet.ServletContextListener
import javax.servlet.ServletContextEvent
import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCP;
import org.squeryl.SessionFactory;
import org.squeryl.Session;
import org.squeryl.adapters.H2Adapter;




class SquerylInitListener extends ServletContextListener
{
	
  def contextInitialized(e: ServletContextEvent):Unit =
  {
     Class.forName("org.h2.Driver");
     val boneCpConfig = new BoneCPConfig();
     boneCpConfig.setJdbcUrl("jdbc:h2:test");
     boneCpConfig.setUsername("sa");
     boneCpConfig.setPassword("");
     connectionPool = new BoneCP(boneCpConfig);
     SessionFactory.concreteFactory = Some(
        () => Session.create(
                  connectionPool.getConnection(),
                  databaseAdapter
              )
     );
  }

   def contextDestroyed(e: ServletContextEvent):Unit =
  {
    if (connectionPool != null) {
       connectionPool.shutdown();
    }
  }

  private var connectionPool: BoneCP = null;
  private var databaseAdapter = new H2Adapter();

  
  

}