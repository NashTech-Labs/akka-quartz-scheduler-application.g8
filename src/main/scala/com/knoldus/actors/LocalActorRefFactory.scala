package com.knoldus.actors

import akka.actor.{ActorSystem, ActorRef, ActorRefFactory, Props}
import akka.routing.RoundRobinPool
import com.knoldus.configuration.Configuration
import com.typesafe.config.ConfigFactory


trait LocalActorRefFactory {

  val actors: Map[String, ActorRef]

  def getReceiver(name: String): ActorRef = {
    actors.get(name) match {
      case None => throw new IllegalArgumentException("No Actor could be looked up for the specified name " + name)
      case Some(actorRef) => actorRef
    }
  }
}

object LocalActorRefFactory extends LocalActorRefFactory{
  val _system: ActorSystem = ActorSystem.create("KnoldusScheduler", ConfigFactory.load)
  val actors: Map[String, ActorRef] = Map(
    Configuration.NAME_FIRST_ACTOR -> _system.actorOf(Props(classOf[FirstActor])
      .withRouter(RoundRobinPool(Runtime.getRuntime.availableProcessors()))),
    Configuration.NAME_SECOND_ACTOR -> _system.actorOf(Props(classOf[SecondActor])
      .withRouter(RoundRobinPool(Runtime.getRuntime.availableProcessors())))
  )

}