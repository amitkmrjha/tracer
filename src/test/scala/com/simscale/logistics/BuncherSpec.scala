package com.simscale.logistics

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Failure
import scala.util.Success
import akka.Done
import akka.NotUsed
import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import akka.actor.testkit.typed.scaladsl.LogCapturing
import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.scaladsl.TimerScheduler
import com.simscale.logistics.OutPut.Protocol
import com.simscale.logistics.actor.Buncher
import com.simscale.logistics.data.LogData
import org.scalatest.wordspec.AnyWordSpecLike

class BuncherSpec  extends ScalaTestWithActorTestKit with AnyWordSpecLike {

  "The buncher patterns docs" must {
    "contain a sample for scheduling messages to self" in {
      val probe = createTestProbe[Protocol]()
      val buncher: ActorRef[Buncher.Command] = spawn(Buncher(probe.ref, 5.second, "TestActor2ID"))
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ctNull)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ctab)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ctac)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ctad)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ct1a)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ct1d)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ct11D)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ct22D)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ct33D)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ct44D)
      buncher ! Buncher.RecordLogEntry( "43", "TestActor2ID", LogData.ct55D)
      probe.expectNoMessage()
/*      probe.expectMessage(
        6.seconds,
        Buncher.Batch(Vector[Buncher.Command](
          Buncher.ExcitingMessage("one"),
          Buncher.ExcitingMessage("two")
        )))*/
      probe.expectMessageType[Protocol](6.seconds)
    }
  }


}
