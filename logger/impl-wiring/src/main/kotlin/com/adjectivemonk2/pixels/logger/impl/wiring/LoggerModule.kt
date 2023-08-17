package com.adjectivemonk2.pixels.logger.impl.wiring

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.LogcatWriter
import co.touchlab.kermit.MessageStringFormatter
import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@ContributesTo(AppScope::class)
@Module
public class LoggerModule {

  @Provides
  public fun logWriter(formatter: MessageStringFormatter): LogWriter = LogcatWriter(formatter)
}
