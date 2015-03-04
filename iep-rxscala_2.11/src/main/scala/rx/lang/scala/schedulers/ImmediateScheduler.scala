/**
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package iep.rx.lang.scala.schedulers

import iep.rx.lang.scala.Scheduler

object ImmediateScheduler {

  /**
   * Returns a [[iep.rx.lang.scala.Scheduler]] that executes work immediately on the current thread.
   */
  def apply(): ImmediateScheduler =  {
    new ImmediateScheduler(iep.rx.schedulers.Schedulers.immediate())
  }
}

class ImmediateScheduler private[scala] (val asJavaScheduler: iep.rx.Scheduler)
  extends Scheduler {}


