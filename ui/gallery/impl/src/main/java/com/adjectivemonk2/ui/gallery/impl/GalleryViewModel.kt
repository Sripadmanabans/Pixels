package com.adjectivemonk2.ui.gallery.impl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.adjectivemonk2.lifecycle.AssistedViewModelFactory
import com.adjectivemonk2.lifecycle.ViewModelKey
import com.adjectivemonk2.network.gallery.GalleryRepository
import com.adjectivemonk2.scope.ActivityScope
import com.squareup.anvil.annotations.ContributesMultibinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import timber.log.debug
import timber.log.error

public class GalleryViewModel @AssistedInject constructor(
  repository: GalleryRepository,
  @Assisted handle: SavedStateHandle,
) : ViewModel() {

  public val state: Flow<String> = handle.getLiveData<String>(SAVED_STATE_KEY).asFlow()

  init {
    repository.getGallery()
      .onEach {
        Timber.debug { "Api response: $it" }
        handle[SAVED_STATE_KEY] = "Success"
      }
      .catch {
        Timber.error(it) { "Api failed" }
        handle[SAVED_STATE_KEY] = "Failure"
      }
      .launchIn(viewModelScope)
  }

  @AssistedFactory
  @ContributesMultibinding(scope = ActivityScope::class)
  @ViewModelKey(GalleryViewModel::class)
  public interface Factory : AssistedViewModelFactory {
    override fun create(handle: SavedStateHandle): GalleryViewModel
  }

  private companion object {
    private const val SAVED_STATE_KEY = "SAVED_STATE_KEY"
  }
}
