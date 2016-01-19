package com.knoldus.actors

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool
import com.knoldus.configuration.Configuration
import com.typesafe.config.ConfigFactory

// $COVERAGE-OFF$Disabling highlighting by default until a workaround for https://issues.scala-lang.org/browse/SI-8596 is found
trait LocalActorRefFactory {

  val actors: Map[String, ActorRef]
  val system: ActorSystem
  def getReceiver(name: String): ActorRef = {
    actors.get(name) match {
      case None => throw new IllegalArgumentException("No Actor could be looked up for the specified name " + name)
      case Some(actorRef) => actorRef
    }
  }
}

object LocalActorRefFactory extends LocalActorRefFactory{
  val system: ActorSystem = ActorSystem.create("KnoldusScheduler", ConfigFactory.load)
  val actors: Map[String, ActorRef] = Map(
    Configuration.NAME_FIRST_ACTOR -> system.actorOf(Props(classOf[FirstActor])
      .withRouter(RoundRobinPool(Runtime.getRuntime.availableProcessors()))),
    Configuration.NAME_SECOND_ACTOR -> system.actorOf(Props(classOf[SecondActor])
      .withRouter(RoundRobinPool(Runtime.getRuntime.availableProcessors())))
  )

}
// $COVERAGE-ON$