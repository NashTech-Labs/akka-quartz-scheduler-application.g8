package actors

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import com.knoldus.actors.SecondActor
import com.knoldus.messages.{Reply, Message1}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class SecondActorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll{

  def this() = this(ActorSystem("SecondActorSpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(_system)
  }

  "SecondActor" must {

    "receive a message Message1 and send back an object Reply" in {
      val probe = TestProbe()
      val halfYearlyActor = _system.actorOf(Props(classOf[SecondActor]))

      probe watch halfYearlyActor

      probe.send(halfYearlyActor, Message1)
      probe.expectMsg(Reply)
    }

  }

}
