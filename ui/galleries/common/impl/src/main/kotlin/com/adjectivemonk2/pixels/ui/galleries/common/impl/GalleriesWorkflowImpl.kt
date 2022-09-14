package com.adjectivemonk2.pixels.ui.galleries.common.impl

import com.adjectivemonk2.pixels.dispatcher.IoDispatcher
import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.network.core.apiCatch
import com.adjectivemonk2.pixels.network.gallery.GalleryRepository
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesWorkflow
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.StatefulWorkflow
import com.squareup.workflow1.action
import com.squareup.workflow1.asWorker
import com.squareup.workflow1.runningWorker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ContributesBinding(
  scope = ActivityScope::class,
  boundType = GalleriesWorkflow::class,
)
public class GalleriesWorkflowImpl @Inject constructor(
  private val repository: GalleryRepository,
  private val galleryConverter: GalleryConverter,
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : GalleriesWorkflow, StatefulWorkflow<Unit, GalleriesState, Unit, GalleriesScreen>() {

  override fun initialState(props: Unit, snapshot: Snapshot?): GalleriesState {
    return GalleriesState.Loading
  }

  override fun render(
    renderProps: Unit,
    renderState: GalleriesState,
    context: RenderContext,
  ): GalleriesScreen {
    return when (renderState) {
      is GalleriesState.Data -> {
        val galleries = renderState.data
        val galleryItems = galleries.mapNotNull { galleryConverter.toGalleryListItem(it) }
        if (galleryItems.isNotEmpty()) {
          GalleriesScreen.Info(galleryItems)
        } else {
          GalleriesScreen.Empty
        }
      }
      is GalleriesState.Error -> {
        GalleriesScreen.Error(
          message = renderState.throwable?.message ?: "Error",
          onRetryClick = context.eventHandler { state = GalleriesState.Loading },
        )
      }
      GalleriesState.Loading -> {
        val gallery = repository.getGallery()
          .map<List<Gallery>, GalleriesState> { GalleriesState.Data(it) }
          .apiCatch { emit(GalleriesState.Error(it)) }
          .flowOn(dispatcher)
          .asWorker()
        context.runningWorker(gallery) { result ->
          action { state = result }
        }
        GalleriesScreen.Loading
      }
    }
  }

  override fun snapshotState(state: GalleriesState): Snapshot? {
    return null
  }
}

public sealed class GalleriesState {
  internal object Loading : GalleriesState()
  internal data class Data(val data: List<Gallery>) : GalleriesState()
  internal data class Error(val throwable: Throwable?) : GalleriesState()
}
