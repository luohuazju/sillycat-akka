package com.sillycat.akka.server

import com.sillycat.akka.actor.EventMessageActor
import com.typesafe.scalalogging.slf4j.Logging
import akka.actor.{ ActorSystem, Actor, Props }
import akka.routing.{ FromConfig, RoundRobinRouter }
import akka.routing.Broadcast
import akka.actor.PoisonPill
import com.sillycat.akka.model.EventMessage

/**
 * Created by carl on 8/1/14.
 */

class EventService extends Logging {}

object EventService extends Logging {

  //private val logger = (new EventService()).logger
  logger.info("Starting EventService...")

  def startOne(item: EventMessage) = {
    router ! item
  }

  def shutdown() = {
    logger.info("Broadcast PoisonPill...")
    router ! Broadcast(PoisonPill)
    logger.info("EventService shut down.")
  }

  private lazy val actorSystem = ActorSystem("EventServiceSystem")
  private lazy val router = actorSystem.actorOf(Props[EventMessageActor].withRouter(FromConfig()), name = "EventMessageRouter")

}
