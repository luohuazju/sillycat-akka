package com.sillycat.akka.server

import akka.actor.{ Props, ActorSystem }
import akka.kernel.Bootable
import com.sillycat.akka.actor.EventMessageActor
import com.typesafe.config.ConfigFactory

class EventServiceRemoteApp extends Bootable {

  val system = ActorSystem("EventServiceRemoteSystem", ConfigFactory.load("remotesystem"))

  def startup = {
    system.actorOf(Props[EventMessageActor], name = "EventMessageRemoteActor")
  }

  def shutdown = {
    system.shutdown()
  }

}
