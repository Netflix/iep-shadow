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
package iep.rx.lang.scala.subscriptions

import iep.rx.lang.scala._

private [scala] object BooleanSubscription {
  def apply(): BooleanSubscription = new BooleanSubscription(new iep.rx.subscriptions.BooleanSubscription())
}

/**
 * Represents a [[iep.rx.lang.scala.Subscription]] that can be checked for status.
 */
private [scala] class BooleanSubscription private[scala] (boolean: iep.rx.subscriptions.BooleanSubscription)
  extends Subscription {

  override val asJavaSubscription: iep.rx.subscriptions.BooleanSubscription = boolean
}
