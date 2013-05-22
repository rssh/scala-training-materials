package protoexample

import org.freeradius._
import org.freeradius.Vsa._
import scala.xml._
import dispatch._
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent._
import scala.concurrent.duration._

object Main
{

 def main(args: Array[String]):Unit =
 {
   Console.println("Hello!")
  
   val webApiEndpoint = "http://192.168.56.101:8088/billing-webapi/rlm"
   
   val packet  = generatePacket();
   
   val bytesToSend = packet.toByteArray();
   
   val f = Http( url(webApiEndpoint).PUT.setBody(bytesToSend) OK as.String)
   
   Await.result(f map { s => System.err.println("received: " + s ) }, 1.second );
   
 }
 
 
 
 def generatePacket(): RequestData =
 {
   RequestData(protocolVersion=2, 
               state=ProcessingState.POSTAUTH, 
               vps=Vector(ValuePair(attribute=53,stringValue=Some("aa"))))
 }

}
