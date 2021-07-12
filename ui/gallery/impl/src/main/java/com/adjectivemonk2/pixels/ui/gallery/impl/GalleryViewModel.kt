package com.adjectivemonk2.pixels.ui.gallery.impl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adjectivemonk2.pixels.lifecycle.AssistedViewModelFactory
import com.adjectivemonk2.pixels.lifecycle.ViewModelKey
import com.adjectivemonk2.pixels.network.gallery.GalleryRepository
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.squareup.anvil.annotations.ContributesMultibinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import timber.log.debug
import timber.log.error

public class GalleryViewModel @AssistedInject constructor(
  repository: GalleryRepository,
  @Assisted private val handle: SavedStateHandle,
) : ViewModel() {

  private var _state by mutableStateOf(handle.get<String>(SAVED_STATE_KEY).orEmpty())
  public var state: String
    get() = _state
    private set(value) {
      _state = value
      handle[SAVED_STATE_KEY] = value
    }

  init {
    repository.getGallery()
      .onEach {
        Timber.debug { "Api response: $it" }
        state = "Success"
      }
      .catch {
        Timber.error(it) { "Api failed" }
        state = "Failure"
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
