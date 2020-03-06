package com.simscale.logistics

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import com.simscale.logistics.OutPut.Protocol
import com.simscale.logistics.actor.{Buncher, Tracer}
import com.simscale.logistics.actor.Tracer.ProcessLog
import com.simscale.logistics.data.LogData
import org.scalatest.wordspec.AnyWordSpecLike

import scala.concurrent.duration._

class TracerSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {

  "TraceManager actor " must {

    "reply to Alive Trace Actor List " in {
      val probe = createTestProbe[Protocol]()
      val managerActor = spawn(Tracer(probe.ref),"Trace-Manager")

      managerActor ! ProcessLog(LogData.ctNull)
      managerActor ! ProcessLog(LogData.ct2Null)
      managerActor ! ProcessLog(LogData.ct3Null)

      managerActor ! ProcessLog(LogData.ctab)
      managerActor ! ProcessLog(LogData.ct2ab)
      managerActor ! ProcessLog(LogData.ct3ab)

      managerActor ! ProcessLog(LogData.ctac)
      managerActor ! ProcessLog(LogData.ct2ac)
      managerActor ! ProcessLog(LogData.ct3ac)

      managerActor ! ProcessLog(LogData.ctad)
      managerActor ! ProcessLog(LogData.ct2ad)
      managerActor ! ProcessLog(LogData.ct3ad)

      managerActor ! ProcessLog(LogData.ct1a)
      managerActor ! ProcessLog(LogData.ct21a)
      managerActor ! ProcessLog(LogData.ct21a)

      managerActor ! ProcessLog(LogData.ct1d)
      managerActor ! ProcessLog(LogData.ct21d)
      managerActor ! ProcessLog(LogData.ct31d)

      managerActor ! ProcessLog(LogData.ctad)
      managerActor ! ProcessLog(LogData.ct2ad)
      managerActor ! ProcessLog(LogData.ct3ad)

      managerActor ! ProcessLog(LogData.ct11D)
      managerActor ! ProcessLog(LogData.ct211D)
      managerActor ! ProcessLog(LogData.ct311D)

      managerActor ! ProcessLog(LogData.ct22D)
      managerActor ! ProcessLog(LogData.ct222D)
      managerActor ! ProcessLog(LogData.ct322D)

      managerActor ! ProcessLog(LogData.ct33D)
      managerActor ! ProcessLog(LogData.ct233D)
      managerActor ! ProcessLog(LogData.ct333D)

      managerActor ! ProcessLog(LogData.ct44D)
      managerActor ! ProcessLog(LogData.ct55D)

      probe.expectNoMessage()
      probe.expectNoMessage( 6.seconds)
      /*      probe.expectMessage(
              6.seconds,
              Buncher.Batch(Vector[Buncher.Command](
                Buncher.ExcitingMessage("one"),
                Buncher.ExcitingMessage("two")
              )))*/
      probe.expectMessageType[Protocol](16.seconds)

    }

  }

}
