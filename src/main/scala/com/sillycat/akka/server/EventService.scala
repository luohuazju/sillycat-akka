package com.sillycat.akka.server

import com.sillycat.akka.actor.{ ActorWatcher, EventMessageActor }
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.slf4j.Logging
import akka.actor._
import akka.routing.{ FromConfig, RoundRobinRouter }
import akka.routing.Broadcast
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

  private lazy val actorSystem = ActorSystem("EventServiceLocalSystem", ConfigFactory.load("localjvm"))
  private lazy val router = actorSystem.actorOf(Props[EventMessageActor].withRouter(FromConfig()), name = "EventMessageLocalRouter")

  private lazy val routerWatcher =
    actorSystem.actorOf(Props(new ActorWatcher(router)), name = "EventMessageLocalRouterWatcher")

}

