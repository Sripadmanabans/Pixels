package com.adjectivemonk2.pixels.local.session

import com.adjectivemonk2.pixels.local.session.model.SessionInfo
import kotlinx.coroutines.flow.Flow

public interface SessionLocalDataSource {

  public suspend fun insert(sessionInfo: SessionInfo)

  public fun getSession(): Flow<SessionInfo>
}
