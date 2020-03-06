package com.simscale.logistics.data

import java.time.Instant

import com.simscale.logistics.domain.{RootEntry, SpanEntry}

object LogData {
  val ctNull = RootEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","aa")
  val ctab = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","aa","ab")
  val ctac = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","aa","ac")
  val ctad = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","ac","ad")
  val ct1a = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","ad","1a")
  val ct1d = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","ac","1d")
  val ct11D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","ad","11D")
  val ct22D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","ad","22D")
  val ct33D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","ad","33D")
  val ct44D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","33D","44D")
  val ct55D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId","front-end","44D","55D")

  val ct2Null = RootEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","aa")
  val ct2ab = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","aa","ab")
  val ct2ac = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","aa","ac")
  val ct2ad = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","ac","ad")
  val ct21a = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","ad","1a")
  val ct21d = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","ac","1d")
  val ct211D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","ad","11D")
  val ct222D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","ad","22D")
  val ct233D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId2","front-end","ad","33D")

  val ct3Null = RootEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","aa")
  val ct3ab = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","aa","ab")
  val ct3ac = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","aa","ac")
  val ct3ad = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","ac","ad")
  val ct31a = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","ad","1a")
  val ct31d = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","ac","1d")
  val ct311D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","ad","11D")
  val ct322D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","ad","22D")
  val ct333D = SpanEntry(Instant.now(),Instant.now(),Instant.now(),"traceId3","front-end","ad","33D")


}
