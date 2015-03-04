package iep.rx.lang.scala.schedulers

import iep.rx.lang.scala.Scheduler

object TrampolineScheduler {
  /**
   * [[iep.rx.lang.scala.Scheduler]] that queues work on the current thread to be executed after the current work completes.
   */
  def apply(): TrampolineScheduler =  {
    new TrampolineScheduler(iep.rx.schedulers.Schedulers.trampoline())
  }
}

class TrampolineScheduler private[scala] (val asJavaScheduler: iep.rx.Scheduler)
  extends Scheduler {}
