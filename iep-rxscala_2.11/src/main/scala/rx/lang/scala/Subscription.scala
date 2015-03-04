/**
 * Copyright 2014 Netflix, Inc.
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

/**
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package iep.rx.lang.scala

import iep.rx.functions.Action0

/**
 * Subscriptions are returned from all `Observable.subscribe` methods to allow unsubscribing.
 *
 * This interface is the equivalent of `IDisposable` in the .NET Rx implementation.
 */
trait Subscription {

  private[scala] val asJavaSubscription: iep.rx.Subscription = iep.rx.subscriptions.BooleanSubscription.create()

  /**
   * Call this method to stop receiving notifications on the Observer that was registered when
   * this Subscription was received.
   */
  def unsubscribe() = {
    asJavaSubscription.unsubscribe()
  }

  /**
   * Checks if the subscription is unsubscribed.
   */
  def isUnsubscribed = {
    asJavaSubscription.isUnsubscribed
  }

}

object Subscription {

  /**
   * Creates an [[iep.rx.lang.scala.Subscription]] from an [[iep.rx.Subscription]].             ß
   */
  private [scala] def apply(subscription: iep.rx.Subscription): Subscription = {
    subscription match {
      case x: iep.rx.subscriptions.BooleanSubscription => new iep.rx.lang.scala.subscriptions.BooleanSubscription(x)
      case x: iep.rx.subscriptions.CompositeSubscription => new iep.rx.lang.scala.subscriptions.CompositeSubscription(x)
      case x: iep.rx.subscriptions.MultipleAssignmentSubscription => new iep.rx.lang.scala.subscriptions.MultipleAssignmentSubscription(x)
      case x: iep.rx.subscriptions.SerialSubscription => new iep.rx.lang.scala.subscriptions.SerialSubscription(x)
      case x: iep.rx.Subscription => new iep.rx.lang.scala.Subscription {
        override val asJavaSubscription = subscription
      }
    }
  }

  /**
   * Creates an [[iep.rx.lang.scala.Subscription]] that invokes the specified action when unsubscribed.
   */
  def apply(u: => Unit): Subscription = new Subscription() {
    override val asJavaSubscription = iep.rx.subscriptions.Subscriptions.create(new Action0 {
      override def call(): Unit = u
    })
  }

  /**
   * Creates an empty [[iep.rx.lang.scala.Subscription]].
   */
  def apply(): Subscription = Subscription {}

}
