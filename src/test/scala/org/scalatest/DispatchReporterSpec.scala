/*
 * Copyright 2001-2013 Artima, Inc.
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
package org.scalatest

import java.io.PrintStream
import java.io.ByteArrayOutputStream
import org.scalatest.events._
import SharedHelpers._

class DispatchReporterSpec extends Spec with Matchers {
  object `the DispatchReporter` {
    def `should accept configuration info regarding slowpoke detection` {
      new DispatchReporter(List.empty, Console.err, true, 60000, 60000)
    }
    object `when slowpoke detection is enabled` {
      def `should send out InfoProvided events if a slowpoke is detected` { pending
        val erp = new EventRecordingReporter
        val dispatch = new DispatchReporter(List(erp), Console.err, true, 1, 1)
        dispatch(
          TestStarting(
            ordinal = new Ordinal(223),
            suiteName = "the suite name",
            suiteId = "the suite ID",
            suiteClassName = Some("suiteClassName"),
            testName = "the test name",
            testText = "test name"
          )
        )
        Thread.sleep(10)
        dispatch.doDispose()
        erp.infoProvidedEventsReceived.size should be > 0
      }
    }
  }
}
