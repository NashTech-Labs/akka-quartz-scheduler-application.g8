package actors

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import com.knoldus.actors.SecondActor
import com.knoldus.messages.Message1
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class SecondActorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll{

  def this() = this(ActorSystem("SecondActorSpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(_system)
  }

  "Half Yearly Actor" must {

    "send the message successfully to HalfYearlyActor with message report" in {
      val probe = TestProbe()
      val halfYearlyActor = _system.actorOf(Props(classOf[SecondActor]))

      probe watch halfYearlyActor

      probe.send(halfYearlyActor, Message1)

    }

  }

}
