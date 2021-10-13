package com.adjectivemonk2.pixels.ui.galleries.common.impl

import javax.inject.Inject

public class AccountImageUrlGenerator @Inject constructor() {

  public fun thumbnail(accountId: String): String {
    return """https://imgur.com/user/$accountId/avatar?maxwidth=290"""
  }
}
