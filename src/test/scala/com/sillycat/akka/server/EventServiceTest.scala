package com.sillycat.akka.server

import com.sillycat.akka.model.EventMessage
import org.joda.time.DateTime
import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite

/**
 * Created by carl on 8/1/14.
 */
class EventServiceTest extends FunSuite with BeforeAndAfter {

  before {

  }

  after {

  }

  test("Testing EventService shutdown only...") {
    EventService.shutdown()
  }

  test("Testing EventService start one...") {
    def item = EventMessage(1, "request1", "request2", "admin", DateTime.now())
    EventService.startOne(item)
  }

  test("Testing EventService Start all...") {
    def item1 = EventMessage(1, "request1", "request1", "admin", DateTime.now())
    def item2 = EventMessage(2, "request2", "request2", "admin", DateTime.now())
    val list = List(item1, item2)
    EventService.startAll(list)
  }

}
