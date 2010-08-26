package net.lag.kestrel.config

import com.twitter.util.Duration
import com.twitter.util.TimeConversions._

trait PersistentQueue {
  lazy val maxItems: Int = Math.MAX_INT
  lazy val maxSize: Long = Math.MAX_LONG
  lazy val maxItemSize: Long = Math.MAX_LONG
  lazy val maxAge: Int = 0
  lazy val maxJournalSize: Long = 16L * 1024 * 1024 // 16MB
  lazy val maxMemorySize: Long = 128L * 1024 * 1024 // 128MB
  lazy val maxJournalOverflow: Int = 10
  lazy val maxJournalSizeAbsolute: Long = Math.MAX_LONG
  lazy val discardOldWhenFull: Boolean = false
  lazy val keepJournal: Boolean = true
  lazy val syncJournal: Boolean = false
  lazy val expiredQueue: Option[String] = None
}

trait Kestrel extends PersistentQueue {
  val queues: Map[String, PersistentQueue]

  lazy val maxThreads: Int = Runtime.getRuntime().availableProcessors * 2
  lazy val listenAddress: String = "0.0.0.0"
  lazy val port = 22133
  lazy val queuePath: String = "/tmp"
  lazy val protocol: String = "ascii"
  lazy val expirationTimerFrequency: Duration = 0.seconds
  lazy val timeout: Duration = 60.seconds
}
