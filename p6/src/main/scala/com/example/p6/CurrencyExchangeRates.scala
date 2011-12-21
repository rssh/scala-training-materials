package com.example.p6;

import scala.io._
import java.net._



object CurrencyLoader
{

  def main(args:Array[String]):Unit =
  {
    val url = new URL("http://pfsoft.com.ua/service/currency/");
    val content = scala.io.Source.fromInputStream( url.openStream(), "cp1251" ).getLines().mkString("\n");
    val xml = scala.xml.XML.loadString(content);
    Console.println(xml.toString);
    val date = (xml \ "@Date" ).text;
    for(v <- xml \ "Valute") {
       v match {
         //case <Valute>{nodes@_*}</Valute> => Console.println("nodes:"+nodes.toString)
        /*
         case <Valute>
               <NumCode>{numCode}</NumCode>
               <CharCode>{charCode}</CharCode>
               <Nominal>{nominal}</Nominal>
               <Name>{name}</Name>
               <Value>{value}</Value>
              </Valute> => Console.println("NumCode="+numCode);
         */
         case <Valute><NumCode>{numCode}</NumCode><CharCode>{charCode}</CharCode><Nominal>{nominal}</Nominal><Name>{name}</Name><Value>{value}</Value></Valute> => 
           Console.println("charCode=%s, numCode=%s, nominal=%s, value=%s".format(charCode, numCode,nominal, value));
               
               
         case _ => /* nothing */
       }
       Console.println("v="+v.toString);
    }

  }
 
}

