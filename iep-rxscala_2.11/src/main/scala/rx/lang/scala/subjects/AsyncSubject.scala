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
package iep.rx.lang.scala.subjects

import iep.rx.lang.scala.Subject

/**
 * Subject that publishes only the last item observed to each `Observer` that has subscribed, when the
 * source `Observable}` completes.
 * <p>
 * <img width="640" height="405" src="https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.AsyncSubject.png" alt="" />
 * <p>
 * @example
 {{{
  // observer will receive no onNext events because the subject.onCompleted() isn't called.
  val subject = AsyncSubject[String]()
  subject.subscribe(observer)
  subject.onNext("one")
  subject.onNext("two")
  subject.onNext("three")

  // observer will receive "three" as the only onNext event.
  val subject = AsyncSubject[String]()
  subject.subscribe(observer)
  subject.onNext("one")
  subject.onNext("two")
  subject.onNext("three")
  subject.onCompleted()
  }}}
 */
object AsyncSubject {
  /**
   * Creates and returns a new `AsyncSubject`
   * @return the new `AsyncSubject`
   */
  def apply[T](): AsyncSubject[T] = {
    new AsyncSubject[T](iep.rx.subjects.AsyncSubject.create())
  }
}

class AsyncSubject[T] private[scala] (val asJavaSubject: iep.rx.subjects.AsyncSubject[T]) extends Subject[T] {}
