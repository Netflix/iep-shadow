package iep.rx.lang.scala.schedulers

import iep.rx.lang.scala.Scheduler


object ComputationScheduler {
  /**
   * [[iep.rx.lang.scala.Scheduler]] intended for computational work.
   * <p>
   * This can be used for event-loops, processing callbacks and other computational work.
   * <p>
   * Do not perform IO-bound work on this scheduler. Use [[iep.rx.lang.scala.schedulers.IOScheduler]] instead.
   *
   * @return [[iep.rx.lang.scala.Scheduler]] for computation-bound work.
   */
  def apply(): ComputationScheduler = {
    new ComputationScheduler(iep.rx.schedulers.Schedulers.computation())
  }
}

class ComputationScheduler private[scala] (val asJavaScheduler: iep.rx.Scheduler)
  extends Scheduler {}
