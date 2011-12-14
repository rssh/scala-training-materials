package com.example.p2.servlet;


import com.example.p2.engine._;
import _root_.com.example.p2.engine.agents._;


import javax.servlet._;
import javax.servlet.http._;

import scala.util.parsing.json._;

import java.util.{ Map => JavaMap }

import org.slf4j._;

class ConsoleServlet extends HttpServlet
{

  override def doPost(req:HttpServletRequest, resp:HttpServletResponse): Unit = {
    
    val op = req.getParameter("op");

    if (!(op eq null)) {
       op match {
         case "login" => doLogin(req,resp);
         case "command"   => doCommand(req,resp);
         case _  => throw new ServletException("op parameter must be command or login, we have "+op);
       }
    }
    resp.setHeader("Content-Type","application/json")
    resp.getWriter().flush();
  }
  
  def doLogin(req:HttpServletRequest, resp:HttpServletResponse): Unit = {
       val login = req.getParameter("login")
       if (login eq null) {
         throw new ServletException("login parameter is missing");
       }     
       val password = req.getParameter("password");
       if (password eq null) {
         throw new ServletException("password parameter is missing");
       }     
       
       if (password != "qqq" ) {
         resp.getWriter().print("{ \"result\":false }");
       } else {

         val token = System.currentTimeMillis().toString();

         req.getSession(true).putValue("login",login);
         req.getSession(true).putValue("token",token);

         TalkEngine.initialized;
         val newAgent = new HumanAgent(login);
         TalkEngine.add(newAgent);
       
         resp.getWriter().print("{ \"result\":true, \"auth_token\": \"%s\" }".format(token)); 
       }
       
       
  }

  def doCommand(req:HttpServletRequest, resp:HttpServletResponse): Unit = {
    
        val message = req.getParameter("message");
        if (message eq null) {
           throw new ServletException("message parameter is absent");
        } 
        
        val ologin = req.getSession().getAttribute("login")
        if (ologin eq null) {
           resp.getWriter().print("{ \"message\": \"You are not logged\" }");
        } else {
          val login = ologin.asInstanceOf[String]
          val agentName = req.getSession().getAttribute("agentName")
          var response = if (agentName eq null) {
                            TalkEngine.chooseAgent(login, message) match {
                              case Left(x) => x
                              case Right(x) => req.getSession().setAttribute("agentName",x);
                                               x
                            }
                         } else {
                            TalkEngine.process(login,agentName.asInstanceOf[String],message)                           
                         }
          resp.getWriter().print("{ \"message\": \""+JSONFormat.quoteString(response)+"\" }");
        }
    
    
  }
  
  val logger = LoggerFactory.getLogger(classOf[ConsoleServlet])
  
}
