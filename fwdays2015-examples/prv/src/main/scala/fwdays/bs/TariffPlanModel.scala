package fwdays.bs

sealed trait Service
{
  type Limits
  def quantity(l:Limits):BigDecimal
}

case class TariffPlan[Limits](val monthlyFee: BigDecimal,
                              val monthlyLimits: Limits,
                              val excessCharge: BigDecimal)

case object Internet extends Service
{
  case class Limits(gb:Int, bandwidth: Int)
  def quantity(l:Limits):BigDecimal = l.gb
}

case object Telephony extends Service 
{
  type Limits = Int
  def quantity(l:Limits):BigDecimal = l
}


trait TariffPlanDSL[S <: Service, L <: Service#Limits]
{

   //100 Hrn monthly per 10 gb bandwidht 100M excess 1 Hrn per 10 Mb
   //TariffPlan


   case class MoneyExpression(v: BigDecimal)
   {
     def monthly(lm: LimitExpression) =
             TariffPlan[L](v,lm.limit,0)
             

     def per(u: QuantityExpression) 
           = new PerExpression(v,u.quantity)
   }

   trait LimitExpression
   {
     def limit: L
   }

   trait QuantityExpression
   {
     def quantity: Int
   }

   case class PerExpression(money:BigDecimal,quantity:Int) 

   implicit class RichInt(v: Int)
   {
     def hrn = new MoneyExpression(v)
   }

   implicit class RichDouble(v: Double)
   {
     def hrn = new MoneyExpression(v)
   }

   implicit class RichTariffPlan(v: TariffPlan[L])
   {
    def excess(q: PerExpression) = 
        v.copy(excessCharge = q.money/q.quantity)
   }

}

object TelephonyDSL extends TariffPlanDSL[Telephony.type, Telephony.Limits]
{

   class MinuteUsage(v: Int) extends LimitExpression
                                with QuantityExpression
   {
     def limit = v  
     def quantity = v
   }

   implicit class MinuteUsageInt(v:Int) 
   {
     def minute = new MinuteUsage(v)
     def minutes = new MinuteUsage(v)
   }

}

object InternetDSL extends TariffPlanDSL[Internet.type,Internet.Limits]
{
   import Internet._

   implicit class GbUsageInt(v:Int) 
   {
     def gb = new InternetLimitExpression(Limits(v,100))
   }

   implicit class InternetLimitExpression(v:Limits) extends LimitExpression
                                                      with QuantityExpression
   {
      def speed(x:Int): InternetLimitExpression 
                 = new InternetLimitExpression(v.copy(bandwidth=x))

      def bandwidth(x:Int) = speed(x)

      def limit = v

      def quantity = v.gb
   }

   implicit class RichTariffPlan(v: TariffPlan[Internet.Limits]) 
                            extends super.RichTariffPlan(v)
   {
       def speed(x:Int) = 
        v.copy(monthlyLimits=v.monthlyLimits.copy(bandwidth=x))
   }

}

object X {

   def main(args: Array[String]):Unit =
   {
    internetDSL(args)
    telephonyDSL(args)
   }
   
   def internetDSL(args: Array[String]):Unit =
   {
     import InternetDSL._
     val oneHrn = 1 hrn;
     System.out.println(s"1 hrn=${oneHrn}")
     val m3 = (30 hrn) monthly (60 gb)
     System.out.println(s"30 grn monthly for 60gb:${m3}")
     val m4 = (30 hrn) monthly ((60 gb) speed 200) excess ((3 hrn) per (1 gb)) 
     System.out.println(s"m4:${m4}")
     val m6 = (20 hrn) monthly (30 gb) speed 100 excess ((3 hrn) per (1 gb)) 
     System.out.println(s"m6:${m6}")
   }

   def telephonyDSL(args: Array[String]):Unit =
   {
     import TelephonyDSL._
     val m5 = (20 hrn) monthly (100 minutes) excess ((0.50 hrn) per (1 minute)) 
     System.out.println(s"m5:${m5}")
   }

}



