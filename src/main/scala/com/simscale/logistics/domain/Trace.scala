package com.simscale.logistics.domain

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

trait Trace{
  def id: String
}
case class ValidTrace(id: String, root: Span)extends Trace
object ValidTrace {
  implicit val basicValidTraceDecoder: Decoder[ValidTrace] = deriveDecoder
  implicit val basicValidTraceEncoder: Encoder[ValidTrace] = deriveEncoder
}
case class OrphanTrace(id: String, messge:String = "Orphan Trace") extends Trace
object OrphanTrace {
  implicit val basicOrphanTraceDecoder: Decoder[OrphanTrace] = deriveDecoder
  implicit val basicOrphanTraceEncoder: Encoder[OrphanTrace] = deriveEncoder
}
