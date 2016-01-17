package actors

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class FirstActorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("FirstActorSpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(_system)
  }

  "Daily Actor" must {

    "send back messages unchanged on testactors echoactgorProps" in {
      val echo = _system.actorOf(TestActors.echoActorProps)
      echo ! "hello world"
      expectMsg("hello world")
    }

  }

}
