package com.adjectivemonk2.pixels.logger.impl

import co.touchlab.kermit.Message
import co.touchlab.kermit.MessageStringFormatter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.Tag
import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class)
public class LogFormatter @Inject constructor() : MessageStringFormatter {

  override fun formatMessage(severity: Severity?, tag: Tag?, message: Message): String {
    val finalMessage = "{${Thread.currentThread().name}} ${message.message}"
    return super.formatMessage(severity, tag, Message(finalMessage))
  }
}
