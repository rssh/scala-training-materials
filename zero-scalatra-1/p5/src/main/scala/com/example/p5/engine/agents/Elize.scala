package com.example.p5.engine.agents

import com.example.p5.engine._
import scala.util.matching._;
import scala.util.Random

/**
 *   a cheezy little Eliza knock-off by Joe Strout <joe@strout.net>
 *   with some updates by Jeff Epler <jepler@inetnebr.com>
 *   hacked into a module and updated by Jez Higgins <jez@jezuk.co.uk>
 *   ported to scala by Ruslan Shevchenko
 */   
object Elize extends FunAnswerAgent {

  final val name = "elize" 
  
  def answer(askingName:String, message: String): String =
  {
    for(p <- matches) {
      p._1 findFirstMatchIn message match {
        case Some(m) =>  var r = Random.nextInt();
                         if (r < 0) r = -r;
                         val tmpl = p._2.toIndexedSeq(r % p._2.length)
                         return tmpl.replaceAll("%1",m.group(1));
        case None => /* do nothing */ 
      }
    }
    "Hmm, nothing interesting in you words found";
  }
  
  val matches:List[(Regex,List[String])]=List(
       "I need (.*)".r -> List(
                            "Why do you need %1?",
                            "Would it really help you to get %1?",
                            "Are you sure you need %1?"
                          ),
                   
        """Why don't " you ([^\?]*)\?""".r -> List(
                             "Do you really think I don't %1?",
                             "Perhaps eventually I will %1.",
                             "Do you really want me to %1?"
                           ),

        """Why can\'?t I ([^\?]*)\?""".r -> List(
                            "Do you think you should be able to %1?",
                            "If you could %1, what would you do?",
                            "I don't know -- why can't you %1?",
                            "Have you really tried?"
                            ),

        """I can\'?t (.*)""".r ->  List(
                             "How do you know you can't %1?",
                             "Perhaps you could %1 if you tried.",
                             "What would it take for you to %1?"
                             ),

        """'I am (.*)""".r -> List(
                               "Did you come to me because you are %1?",
                               "How long have you been %1?",
                               "How do you feel about being %1?"
                              ),

         """I\'?m (.*)""".r -> List(
                                  "How does being %1 make you feel?",
                                  "Do you enjoy being %1?",
                                  "Why do you tell me you're %1?",
                                  "Why do you think you're %1?"
                                ),

         """Are you ([^\?]*)\??""".r -> List(
                                   "Why does it matter whether I am %1?",
                                   "Would you prefer it if I were not %1?",
                                   "Perhaps you believe I am %1.",
                                   "I may be %1 -- what do you think?"),

          """What (.*)""".r -> List(
                                 "Why do you ask?",
                                 "How would an answer to that help you?",
                                 "What do you think?"),

           """How (.*)""".r -> List(
                                  "How do you suppose?",
                                  "Perhaps you can answer your own question.",
                                  "What is it you're really asking?"),
                              
           """Because (.*)""".r -> List(
                                   "Is that the real reason?",
                                   "What other reasons come to mind?",
                                   "Does that reason apply to anything else?",
                                   "If %1, what else must be true?"),

           """(.*) sorry (.*)""".r -> List(
                        "There are many times when no apology is needed.",
                        "What feelings do you have when you apologize?"),

           """Hello(.*)""".r -> List(
                           "Hello... I'm glad you could drop by today.",
                           "Hi there... how are you today?",
                           "Hello, how are you feeling today?"),

           """I think (.*)""".r -> List(
                           "Do you doubt %1?",
                           "Do you really think so?",
                           "But you're not sure %1?"),

          """(.*) friend (.*)""".r -> List(
                            "Tell me more about your friends.",
                            "When you think of a friend, what comes to mind?",
                            "Why don't you tell me about a childhood friend?"),

          """Yes""".r -> List(
                             "You seem quite sure.",
                             "OK, but can you elaborate a bit?"),

          """(.*) computer(.*)""".r -> List(
                              "Are you really talking about me?",
                              "Does it seem strange to talk to a computer?",
                              "How do computers make you feel?",
                              "Do you feel threatened by computers?"),

          """Is it (.*)""".r -> List(
                               "Do you think it is %1?",
                               "Perhaps it's %1 -- what do you think?",
                               "If it were %1, what would you do?",
                               "It could well be that %1."),
                               
          """It is (.*)""".r -> List(
              "You seem very certain.",
              "If I told you that it probably isn't %1, what would you feel?"),

          """Can you ([^\?]*)\??""".r -> List(
                                 "What makes you think I can't %1?",
                                 "If I could %1, then what?",
                                 "Why do you ask if I can %1?"),

          """Can I ([^\?]*)\??""".r -> List(
                              "Perhaps you don't want to %1.",
                              "Do you want to be able to %1?",
                              "If you could %1, would you?"),

          """You are (.*)""".r -> List(
                                  "Why do you think I am %1?",
                                  "Does it please you to think that I'm %1?",
                                  "Perhaps you would like me to be %1.",
                                  "Perhaps you're really talking about yourself?"),                           
                    
          """You\'?re (.*)""".r -> List(
                                    "Why do you say I am %1?",
                                    "Why do you think I am %1?",
                                    "Are we talking about you, or me?"),

          """I don\'?t (.*)""".r -> List(
                                    "Don't you really %1?",
                                    "Why don't you %1?",
                                    "Do you want to %1?"),

          """I feel (.*)""".r -> List(
                                   "Good, tell me more about these feelings.",
                                   "Do you often feel %1?",
                                   "When do you usually feel %1?",
                                   "When you feel %1, what do you do?"),

          """I have (.*)""".r -> List(
                                  "Why do you tell me that you've %1?",
                                  "Have you really %1?",
                                  "Now that you have %1, what will you do next?"),
                          
          """I would (.*)""".r -> List(
                                  "Could you explain why you would %1?",
                                  "Why would you %1?",
                                  "Who else knows that you would %1?"),

          """Is there (.*)""".r -> List(
                                   "Do you think there is %1?",
                                   "It's likely that there is %1.",
                                   "Would you like there to be %1?"),

          """My (.*)""".r -> List(
                                "I see, your %1.",
                                "Why do you say that your %1?",
                                "When your %1, how do you feel?"),

          """You (.*)""".r -> List(
    "We should be discussing you, not me.",
    "Why do you say that about me?",
    "Why do you care whether I %1?"),
    
          """Why (.*)""".r -> List(
    "Why don't you tell me the reason why %1?",
    "Why do you think %1?" ),

          """I want (.*)""".r -> List(
    "What would it mean to you if you got %1?",
    "Why do you want %1?",
    "What would you do if you got %1?",
    "If you got %1, then what would you do?"),

     """(.*) mother(.*)""".r -> List(
    "Tell me more about your mother.",
    "What was your relationship with your mother like?",
    "How do you feel about your mother?",
    "How does this relate to your feelings today?",
    "Good family relations are important."),

     """(.*) father(.*)""".r -> List(
    "Tell me more about your father.",
    "How did your father make you feel?",
    "How do you feel about your father?",
    "Does your relationship with your father relate to your feelings today?",
    "Do you have trouble showing affection with your family?"),
    
     """(.*) child(.*)""".r -> List(
    "Did you have close friends as a child?",
    "What is your favorite childhood memory?",
    "Do you remember any dreams or nightmares from childhood?",
    "Did the other children sometimes tease you?",
    "How do you think your childhood experiences relate to your feelings today?"),

     """(.*)\?""".r -> List(
    "Why do you ask that?",
    "Please consider whether you can answer your own question.",
    "Perhaps the answer lies within yourself?",
    "Why don't you tell me?"),

      """quit""".r -> List(
    "Thank you for talking with me.",
    "Good-bye.",
    "Thank you, that will be $150.  Have a good day!"),

      """(.*)""".r -> List(
    "Please tell me more.",
    "Let's change focus a bit... Tell me about your family.",
    "Can you elaborate on that?",
    "Why do you say that %1?",
    "I see.",
    "Very interesting.",
    "%1.",
    "I see.  And what does that tell you?",
    "How does that make you feel?",
    "How do you feel when you say that?")
                        
  );
  
}
