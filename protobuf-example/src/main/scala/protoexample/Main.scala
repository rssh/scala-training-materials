package protoexample

import org.freeradius._


object Main
{

 def main(args: Array[String]):Unit =
 {
   Console.println("Hello!")
   
   
 }
 
 
 def generatePacket(): RequestData =
 {
   RequestData(protocolVersion=2, 
               state=ProcessingState.POSTAUTH, 
               vps=Vector(ValuePair(attribute=53,stringValue=Some("aa"))))
 }

}
