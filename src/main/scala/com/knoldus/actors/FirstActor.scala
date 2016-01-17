package com.knoldus.actors

import akka.actor.{Actor, ActorLogging}
import com.knoldus.messages._

class FirstActor extends Actor with ActorLogging{

  override def receive: Receive = {
    case Message1 => {
      log.info("<<<<First actor is up for message 1>>>>")
    }
    case Message2 => {
      log.info("<<<<First actor is up for message 2>>>>")
    }
  }

}
