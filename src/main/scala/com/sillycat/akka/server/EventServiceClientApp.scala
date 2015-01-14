package com.sillycat.akka.server

import akka.actor._
import akka.routing.{ Broadcast, FromConfig }
import com.sillycat.akka.actor.{ ActorWatcher, EventMessageLocalActor, EventMessageActor }
import com.sillycat.akka.model.EventMessage
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.slf4j.Logging
import org.joda.time.DateTime

/**
 * Created by carl on 8/11/14.
 */
object EventServiceClientApp extends App {

  val system = ActorSystem("EventServiceLocalSystem", ConfigFactory.load("clientsystem"))
  val remotePath =
    "akka.tcp://EventServiceRemoteSystem@10.190.191.15:2552/user/EventMessageRemoteActor"
  val clientActor = system.actorOf(Props(classOf[EventMessageLocalActor], remotePath), "EventMessageClientActor")

  private lazy val routerWatcher =
    system.actorOf(Props(new ActorWatcher(clientActor)), name = "EventMessageClientRouterWatcher")

  Range(1, 10) foreach { i =>
    def item = EventMessage(1, "request1", "request2", "admin", DateTime.now())
    clientActor ! "fire works."
    clientActor ! item
  }

  Thread.sleep(5000)

  clientActor ! Broadcast(PoisonPill)
  system.shutdown()

}

