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
package iep.rx.lang.scala

/**
 * These functions convert between RxScala types RxJava types.
 * Pure Scala projects won't need them, but they will be useful for polyglot projects.
 * This object only contains conversions between types. For conversions between functions,
 * use [[iep.rx.lang.scala.ImplicitFunctionConversions]].
 */
object JavaConversions {
  import language.implicitConversions
  
  implicit def toJavaNotification[T](s: Notification[T]): iep.rx.Notification[_ <: T] = s.asJavaNotification
  
  implicit def toScalaNotification[T](s: iep.rx.Notification[_ <: T]): Notification[T] = Notification(s)

  implicit def toJavaSubscription(s: Subscription): iep.rx.Subscription = s.asJavaSubscription
  
  implicit def toScalaSubscription(s: iep.rx.Subscription): Subscription = Subscription(s)

  implicit def toJavaSubscriber[T](s: Subscriber[T]): iep.rx.Subscriber[_ >: T] = s.asJavaSubscriber
  
  implicit def toScalaSubscriber[T](s: iep.rx.Subscriber[_ >: T]): Subscriber[T] = Subscriber(s)
  
  implicit def scalaSchedulerToJavaScheduler(s: Scheduler): iep.rx.Scheduler = s.asJavaScheduler
  implicit def javaSchedulerToScalaScheduler(s: iep.rx.Scheduler): Scheduler = Scheduler(s)

  implicit def scalaWorkerToJavaWorker(s: Worker): iep.rx.Scheduler.Worker = s.asJavaWorker
  implicit def javaWorkerToScalaWorker(s: iep.rx.Scheduler.Worker): Worker = Worker(s)


  implicit def toJavaObserver[T](s: Observer[T]): iep.rx.Observer[_ >: T] = s.asJavaObserver
  
  implicit def toScalaObserver[T](s: iep.rx.Observer[_ >: T]): Observer[T] = Observer(s)

  implicit def toJavaObservable[T](s: Observable[T]): iep.rx.Observable[_ <: T] = s.asJavaObservable
  
  implicit def toScalaObservable[T](observable: iep.rx.Observable[_ <: T]): Observable[T] = {
    new Observable[T]{
      val asJavaObservable = observable
    }
  }

  implicit def toJavaOperator[T, R](operator: Subscriber[R] => Subscriber[T]): iep.rx.Observable.Operator[R, T] = {
    new iep.rx.Observable.Operator[R, T] {
      override def call(subscriber: iep.rx.Subscriber[_ >: R]): iep.rx.Subscriber[_ >: T] = {
        toJavaSubscriber[T](operator(toScalaSubscriber[R](subscriber)))
      }
    }
  }
}
