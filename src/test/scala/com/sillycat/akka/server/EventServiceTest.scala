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

  test("Testing EventService start one...") {
    def item = EventMessage(1, "request1", "request2", "admin", DateTime.now())
    Range(1, 10) foreach { i =>
      EventService.startOne(item)
    }
    Thread.sleep(1000)
    EventService.shutdown()
  }

}
