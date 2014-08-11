package com.sillycat.akka.server

import akka.actor.{ Props, ActorSystem }
import com.sillycat.akka.actor.EventMessageLocalActor
import com.sillycat.akka.model.EventMessage
import com.typesafe.config.ConfigFactory
import org.joda.time.DateTime

/**
 * Created by carl on 8/11/14.
 */
object EventServiceLocalApp extends App {

  val system = ActorSystem("EventServiceLocalSystem", ConfigFactory.load(("LocalSystem")))

  val localActor = system.actorOf(Props[EventMessageLocalActor], name = "EventMessageLocalActor")
  //def item = EventMessage(1, "request1", "request2", "admin", DateTime.now())
  localActor ! "Fire works."

  Thread.sleep(2000)
  system.shutdown()

}
