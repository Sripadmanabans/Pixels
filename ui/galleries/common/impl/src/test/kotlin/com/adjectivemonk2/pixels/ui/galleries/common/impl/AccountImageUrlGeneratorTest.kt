package com.adjectivemonk2.pixels.ui.galleries.common.impl

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class AccountImageUrlGeneratorTest {

  @Test fun `Account thumbnail url generator test`() {
    val id = "account id"
    val actual = AccountImageUrlGenerator().thumbnail(id)
    val expected = """https://imgur.com/user/$id/avatar?maxwidth=290"""
    assertThat(actual).isEqualTo(expected)
  }
}
