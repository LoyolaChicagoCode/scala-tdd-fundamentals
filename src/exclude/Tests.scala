/**
 * Created by gkt on 12/12/14.
 */
package org.scalatddpackt

import org.junit.Test
import org.junit.Assert._

/**
 * Simple JUnit-based tests.
 */
class Tests {

  @Test def testTrue(): Unit = {
    assertTrue(true);
  }

  @Test def testFalse(): Unit = {
    assertFalse(false);
  }

  @Test def testDouble(): Unit = {
    assertEquals(1.01, 1, 0.1)

  }
}
