package fwdays.bs

import org.joda.time.DateTime
import com.github.nscala_time.time.Imports._
import scala.util.Try

case class Subscriber(
    val id: Long,
    val name: String,
    val account: BigDecimal,
   //  -- look on shapeless for this
    //val serviceInfos: Map[Service,SubscriberServiceInfo[_,_]],
    val internetUsageInfo: Option[SubscriberServiceInfo[Internet.type,
                                                    Internet.Limits]],
    val telephonyUsageInfo: Option[SubscriberServiceInfo[Telephony.type,
                                                         Telephony.Limits]],
    val lastPayedDate: DateTime
) {

   def serviceInfo(s:Service):Option[SubscriberServiceInfo[s.type,s.Limits]] =
    (s match {
       case Internet => internetUsageInfo map(asS(s)(_))
       case Telephony => telephonyUsageInfo map (asS(s)(_))
    })

   def updateServiceInfo[S<:Service,L<:S#Limits](s: SubscriberServiceInfo[S,L]): Subscriber =
         copy( internetUsageInfo = if (s.service == Internet) 
                                       Some(asS(Internet)(s)) else internetUsageInfo,
               telephonyUsageInfo = if (s.service == Telephony) 
                                       Some(asS(Telephony)(s)) else telephonyUsageInfo
         )

   private def asS[S<:Service, L<:S#Limits](s:Service)(x:SubscriberServiceInfo[S,L])=
         x.asInstanceOf[SubscriberServiceInfo[s.type,s.Limits]]

}

case class SubscriberServiceInfo[S<:Service,L<:S#Limits](
  val service: S,
  val tariffPlan: TariffPlan[L],
  val amountUsed: Double
)

case class ServiceUsage(
   val service: Service,
   val amount: Double,
   val when: DateTime
)

case class Payment(
   val amount: BigDecimal,
   val when: DateTime
)

trait BillingService
{

   def checkServiceAccess(subscriber:Subscriber,service:Service):Boolean =
   {
      import subscriber._
      serviceInfo(service).isDefined && (account > 0)
   }

   //def rateServiceUsage(subscriber:Subscriber,usage: ServiceUsage): Subscriber =
   // reflect business problems in 
   def rateServiceUsage(subscriber:Subscriber,usage: ServiceUsage): Try[Subscriber] =
   Try{
      subscriber.serviceInfo(usage.service) match {
         case Some(SubscriberServiceInfo(service,tariffPlan,amountUsed)) =>
                val limitAmount = service.quantity(tariffPlan.monthlyLimits)
                val price = if (amountUsed + usage.amount < limitAmount)
                               BigDecimal(0)
                            else {
                              val amountToCharge: BigDecimal = if (amountUsed >= limitAmount) 
                                                     usage.amount
                                                   else
                                                     amountUsed + usage.amount - limitAmount
                              amountToCharge * tariffPlan.excessCharge
                            }
                subscriber.copy(account = subscriber.account - price).
                           updateServiceInfo(
                              SubscriberServiceInfo(service, tariffPlan, amountUsed + usage.amount)
                           )
          case None => 
                 throw new IllegalStateException("subscriber is not use this service")
      }
   }
        
   def ratePeriod(subscriber: Subscriber, date: DateTime): Subscriber =
        if (date < subscriber.lastPayedDate) {
           subscriber                 
        } else {
           val price = (
                 subscriber.internetUsageInfo.map(_.tariffPlan.monthlyFee).getOrElse(BigDecimal(0)) +
                 subscriber.telephonyUsageInfo.map(_.tariffPlan.monthlyFee).getOrElse(BigDecimal(0)) )
           subscriber.copy(account = subscriber.account - price, 
                           lastPayedDate = subscriber.lastPayedDate + 1.month)
        }
                                                 
   def enableService[S<:Service, L<:S#Limits](subscriber:Subscriber, service:S, plan:TariffPlan[L]):Subscriber =
        subscriber.serviceInfo(service) match {
           case None => subscriber.updateServiceInfo(SubscriberServiceInfo(service,plan,0.0))
           case Some(oldInfo) =>
                        subscriber.updateServiceInfo(oldInfo.copy(tariffPlan=plan))
        }
    
   def disableService(subscriber:Subscriber, service:Service):Subscriber =
        service match {
           case Internet => subscriber.copy(internetUsageInfo=None)
           case Telephony => subscriber.copy(telephonyUsageInfo=None)
        }

   def acceptPayment(subscriber:Subscriber,payment:Payment): Subscriber =
         subscriber.copy(account = subscriber.account + payment.amount)

}
