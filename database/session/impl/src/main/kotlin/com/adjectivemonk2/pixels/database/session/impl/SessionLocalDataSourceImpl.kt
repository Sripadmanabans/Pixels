package com.adjectivemonk2.pixels.database.session.impl

import com.adjectivemonk2.pixels.database.session.PixelsDb
import com.adjectivemonk2.pixels.database.session.SessionLocalDataSource
import com.adjectivemonk2.pixels.database.session.model.SessionInfo
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
public class SessionLocalDataSourceImpl @Inject constructor(
  private val pixelsDb: PixelsDb,
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
      .mapToList()
      .flatMapConcat { it.asFlow() }
      .map { session -> SessionInfo(session.accessToken, session.refreshToken, session.expiresIn) }
  }
}
