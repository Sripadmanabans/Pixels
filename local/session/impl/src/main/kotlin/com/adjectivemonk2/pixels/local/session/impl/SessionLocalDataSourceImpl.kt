package com.adjectivemonk2.pixels.local.session.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.adjectivemonk2.pixels.disptacher.CoroutineDispatchers
import com.adjectivemonk2.pixels.local.session.PixelsDb
import com.adjectivemonk2.pixels.local.session.SessionLocalDataSource
import com.adjectivemonk2.pixels.local.session.model.SessionInfo
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
public class SessionLocalDataSourceImpl @Inject constructor(
  private val pixelsDb: PixelsDb,
  private val dispatchers: CoroutineDispatchers,
) : SessionLocalDataSource {

  override suspend fun insert(sessionInfo: SessionInfo) {
    pixelsDb.sessionQueries.insert(
      sessionInfo.accessToken,
      sessionInfo.refreshToken,
      sessionInfo.expiresIn,
    )
  }

  override fun getSession(): Flow<SessionInfo> {
    return pixelsDb.sessionQueries.getSession()
      .asFlow()
      .mapToList(dispatchers.io)
      .flatMapConcat { it.asFlow() }
      .map { session -> SessionInfo(session.accessToken, session.refreshToken, session.expiresIn) }
  }
}
