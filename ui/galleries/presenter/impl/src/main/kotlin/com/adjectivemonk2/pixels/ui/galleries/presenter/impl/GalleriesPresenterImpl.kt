package com.adjectivemonk2.pixels.ui.galleries.presenter.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.remote.core.apiCatch
import com.adjectivemonk2.pixels.repository.gallery.GalleryRepository
import com.adjectivemonk2.pixels.repository.gallery.Section
import com.adjectivemonk2.pixels.repository.gallery.Sort
import com.adjectivemonk2.pixels.repository.gallery.Window
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesEvent
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesPresenter
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesScreen
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleryListItem
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ContributesBinding(scope = ActivityScope::class)
public class GalleriesPresenterImpl @Inject constructor(
  private val repository: GalleryRepository,
  private val galleryConverter: GalleryConverter,
) : GalleriesPresenter {

  @Composable override fun present(events: Flow<GalleriesEvent>): GalleriesScreen {
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    val stateItems = remember { mutableStateListOf<GalleryListItem>() }
    val items by remember { derivedStateOf { stateItems.toList() } }
    val state by remember { derivedStateOf { GalleriesScreen(items, isLoading, error) } }
    var apiKey by remember { mutableStateOf(Key()) }
    LaunchedEffect(apiKey) {
      repository.getGallery(apiKey.section, apiKey.sort, apiKey.window)
        .map<List<Gallery>, GalleriesState> { GalleriesState.Data(it) }
        .apiCatch { emit(GalleriesState.Error(it.message ?: "Error")) }
        .onStart { emit(GalleriesState.Loading) }
        .collect { apiState ->
          when (apiState) {
            is GalleriesState.Data -> {
              isLoading = false
              error = null
              stateItems.clear()
              stateItems.addAll(apiState.data.mapNotNull { galleryConverter.toGalleryListItem(it) })
            }

            is GalleriesState.Error -> {
              isLoading = false
              error = apiState.message
              stateItems.clear()
            }

            GalleriesState.Loading -> {
              isLoading = true
              error = null
              stateItems.clear()
            }
          }
        }
    }
    LaunchedEffect(Unit) {
      events.collectLatest { event ->
        when (event) {
          GalleriesEvent.Retry -> apiKey = apiKey.copy(id = apiKey.id + 1)
        }
      }
    }
    return state
  }

  private sealed class GalleriesState {
    data object Loading : GalleriesState()
    data class Data(val data: List<Gallery>) : GalleriesState()
    data class Error(val message: String) : GalleriesState()
  }

  private data class Key(
    val section: Section = Section.HOT,
    val sort: Sort = Sort.VIRAL,
    val window: Window = Window.DAY,
    val id: Int = 0,
  )
}
