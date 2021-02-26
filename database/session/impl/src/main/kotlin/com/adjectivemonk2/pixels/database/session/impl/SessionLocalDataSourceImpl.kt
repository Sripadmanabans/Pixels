package com.adjectivemonk2.pixels.database.session.impl

import com.adjectivemonk2.pixels.dagger.scope.AppScope
import com.adjectivemonk2.pixels.dagger.scope.SingleIn
import com.adjectivemonk2.pixels.database.session.PixelsDb
import com.adjectivemonk2.pixels.database.session.SessionLocalDataSource
import com.adjectivemonk2.pixels.database.session.model.SessionInfo
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(scope = AppScope::class)
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
