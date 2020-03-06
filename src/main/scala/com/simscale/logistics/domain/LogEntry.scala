package com.simscale.logistics.domain

import java.time.Instant

trait LogEntry{
  def inputTimeStamp: Instant
  def startTimeStamp: Instant
  def endTimeStamp: Instant
  def trace: String
  def serviceName: String
  def callerSpan:String
  def span: String
}
object LogEntry {
  def toLogEntry(line : String):LogEntry = {
    try {
      val spanSplit: Array[String] =  line.split("->")
      val spaceSplit = spanSplit(0).split("\\s+");
      val span = spanSplit(1)
      val startTime = Instant.parse(spaceSplit(0))
      val endTime = Instant.parse(spaceSplit(1))
      val traceId = spaceSplit(2)
      val serviceName = spaceSplit(3)
      val callerSpan = spaceSplit(4)
      if(callerSpan == "null") RootEntry(Instant.now(),startTime,endTime,traceId,serviceName,span )
      else SpanEntry(Instant.now(),startTime,endTime,traceId,serviceName,callerSpan,span)
    }catch{
      case ex: Exception => MalformedEntry(Instant.now(),ex.getMessage)
    }
  }
}
case class RootEntry(inputTimeStamp: Instant,startTimeStamp: Instant, endTimeStamp:Instant, trace: String, serviceName: String, span: String ) extends LogEntry {
  override val callerSpan: String = s"null"
}
case class SpanEntry(inputTimeStamp: Instant,startTimeStamp: Instant, endTimeStamp:Instant, trace: String, serviceName: String, callerSpan:String, span: String ) extends LogEntry

case class MalformedEntry(inputTimeStamp: Instant,statusMessage: String ) extends LogEntry {

  override val startTimeStamp: Instant = Instant.now()

  override val endTimeStamp: Instant = Instant.now()

  override val trace: String = ""

  override val serviceName: String = ""

  override val callerSpan: String = ""

  override val span: String = ""
}
