package com.adjectivemonk2.pixels.timber

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

// Need this to be make sure that we can call connectedCheck works.
@RunWith(AndroidJUnit4::class)
internal class LoggingTreeTest {

  @Test fun stupidTest() {
    assertThat(1L + 1L).isEqualTo(2L)
  }
}
