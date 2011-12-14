package com.example.p2.engine

import agents._;

object TalkEngine extends SimpleTalkAgentRegistry(
                             Map("elize" -> Elize,
                                 "tom" -> YesSir)
                          )
                       with FederatedMessageProcessor 
                       with ConsoleLogged
{

     
}