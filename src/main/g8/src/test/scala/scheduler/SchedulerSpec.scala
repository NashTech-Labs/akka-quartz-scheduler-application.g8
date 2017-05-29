package scheduler

import akka.actor._
import akka.routing.RoundRobinPool
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import com.knoldus.actors.LocalActorRefFactory
import com.knoldus.configuration.Configuration._
import com.knoldus.messages.{Message1, Message2}
import com.knoldus.scheduler.Scheduler
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

import scala.concurrent.duration._

class SchedulerSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with MustMatchers with BeforeAndAfterAll{

  def this() = this(ActorSystem("SchedulerSpec", ConfigFactory.load("test.conf")))

  override def afterAll {
    TestKit.shutdownActorSystem(_system)
  }

  object ScheduleTestReceiver extends Actor with ActorLogging {
    var probe: ActorRef = _
    def receive = {
      case NewProbe(_p) =>
        probe = _p
      case Message1 =>
        log.info("Got the Message1")
        probe ! Tock
      case Message2 =>
        log.info("Got the Message2")
        probe ! Tock
    }
  }
    object TestActorRefFactory extends LocalActorRefFactory{
      val system: ActorSystem = _system
      val actors: Map[String, ActorRef] = Map(
        NAME_FIRST_ACTOR -> system.actorOf(Props(ScheduleTestReceiver)
          .withRouter(RoundRobinPool(NO_OF_INSTANCE.toInt))),
        NAME_SECOND_ACTOR -> system.actorOf(Props(ScheduleTestReceiver)
          .withRouter(RoundRobinPool(NO_OF_INSTANCE.toInt)))
      )
    }

    object TestScheduler extends Scheduler{
      val localActorRefFactory: LocalActorRefFactory = TestActorRefFactory
    }

  case class NewProbe(probe: ActorRef)
  case object Tick
  case object Tock

  "Scheduler" must {

    "successfully schedule all the actors" in {
      val receiver = _system.actorOf(Props(ScheduleTestReceiver))
      val probe = TestProbe()
      receiver ! NewProbe(probe.ref)
      TestScheduler.schedule

      val receipt = probe.receiveWhile(Duration(10, SECONDS), Duration(15, SECONDS), 20) {
        case Tock =>
          Tock
      }

      receipt must contain(Tock)
      receipt must have size(3)

    }
  }
}
