package net.lag.kestrel.config

import com.twitter.util.Duration
import com.twitter.util.TimeConversions._

trait PersistentQueue {
  def maxItems: Int = Math.MAX_INT
  def maxSize: Long = Math.MAX_LONG
  def maxItemSize: Long = Math.MAX_LONG
  def maxAge: Int = 0
  def maxJournalSize: Long = 16L * 1024 * 1024 // 16MB
  def maxMemorySize: Long = 128L * 1024 * 1024 // 128MB
  def maxJournalOverflow: Int = 10
  def maxJournalSizeAbsolute: Long = Math.MAX_LONG
  def discardOldWhenFull: Boolean = false
  def keepJournal: Boolean = true
  def syncJournal: Boolean = false
  def expiredQueue: Option[String] = None

  def apply(persistencePath: String, name: String): kestrel.PersistentQueue = {
    new kestrel.PersistentQueue(persistencePath, name, this)
  }
}

trait Kestrel extends PersistentQueue {
  def queues: Map[String, PersistentQueue]

  def maxThreads: Int = Runtime.getRuntime().availableProcessors * 2
  def listenAddress: String = "0.0.0.0"
  def port = 22133
  def queuePath: String = "/tmp"
  def protocol: String = "ascii"
  def expirationTimerFrequency: Duration = 0.seconds
  def timeout: Duration = 60.seconds
}
