package com.adjectivemonk2.pixels.ui.gallery.impl

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adjectivemonk2.pixels.lifecycle.AssistedViewModelFactory
import com.adjectivemonk2.pixels.lifecycle.ViewModelKey
import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.network.gallery.GalleryRepository
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.squareup.anvil.annotations.ContributesMultibinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

public class GalleryViewModel @AssistedInject constructor(
  repository: GalleryRepository,
  @Assisted private val handle: SavedStateHandle,
) : ViewModel() {

  private val _state = mutableStateListOf<Gallery>()
  public val state: List<Gallery> = _state

  init {
    Timber.d("Handle: ${handle.hashCode()}")
    repository.getGallery()
      .map { galleries -> galleries.filter { !it.media.isNullOrEmpty() } }
      .onEach {
        _state.clear()
        _state.addAll(it)
      }
      .catch {
        Timber.e(it, "Api failed")
      }
      .launchIn(viewModelScope)
  }

  @AssistedFactory
  @ContributesMultibinding(scope = ActivityScope::class)
  @ViewModelKey(GalleryViewModel::class)
  public interface Factory : AssistedViewModelFactory {
    override fun create(handle: SavedStateHandle): GalleryViewModel
  }
}
