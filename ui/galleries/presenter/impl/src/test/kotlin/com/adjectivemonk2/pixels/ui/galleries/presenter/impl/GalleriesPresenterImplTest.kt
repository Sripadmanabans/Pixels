package com.adjectivemonk2.pixels.ui.galleries.presenter.impl

import app.cash.molecule.RecompositionClock
import app.cash.molecule.moleculeFlow
import app.cash.turbine.test
import com.adjectivemonk2.pixels.repository.gallery.fake.ExceptionType
import com.adjectivemonk2.pixels.repository.gallery.fake.FakeGalleryRepository
import com.adjectivemonk2.pixels.repository.gallery.fake.TestRuntimeException
import com.adjectivemonk2.pixels.repository.gallery.fake.galleryWithMedia1
import com.adjectivemonk2.pixels.repository.gallery.fake.galleryWithMedia2
import com.adjectivemonk2.pixels.testing.DispatcherParameterResolver
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesEvent
import com.adjectivemonk2.pixels.ui.galleries.presenter.GalleriesScreen
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@Suppress("JUnitMalformedDeclaration")
@ExtendWith(value = [DispatcherParameterResolver::class])
internal class GalleriesPresenterImplTest {

  @DisplayName("Testing api success cases")
  @Nested
  inner class ApiSuccess {

    @Test
    fun `Loading - Empty list`(dispatcher: TestDispatcher) = runTest(dispatcher) {
      val events = MutableSharedFlow<GalleriesEvent>(extraBufferCapacity = 1)
      val repository = FakeGalleryRepository()
      val galleryConverter = GalleryConverter(MediaConverter(), AccountImageUrlGenerator())
      val presenter = GalleriesPresenterImpl(repository, galleryConverter)
      moleculeFlow(RecompositionClock.Immediate) { presenter.present(events) }
        .test {
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), true, null))
          dispatcher.scheduler.advanceUntilIdle()
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), false, null))
          ensureAllEventsConsumed()
          cancel()
        }
    }

    @Test
    fun `Loading - With data`(dispatcher: TestDispatcher) = runTest(dispatcher) {
      val events = MutableSharedFlow<GalleriesEvent>(extraBufferCapacity = 1)
      val galleries = listOf(galleryWithMedia1, galleryWithMedia2)
      val galleryConverter = GalleryConverter(MediaConverter(), AccountImageUrlGenerator())
      val galleryItems = galleries.mapNotNull { galleryConverter.toGalleryListItem(it) }
      val repository = FakeGalleryRepository().apply { setGalleries(galleries) }
      val presenter = GalleriesPresenterImpl(repository, galleryConverter)
      moleculeFlow(RecompositionClock.Immediate) { presenter.present(events) }
        .test {
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), true, null))
          dispatcher.scheduler.advanceUntilIdle()
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(galleryItems, false, null))
          ensureAllEventsConsumed()
          cancel()
        }
    }
  }

  @DisplayName("Testing api error cases")
  @Nested
  inner class ApiError {

    @Test
    fun `Loading - IO exception`(dispatcher: TestDispatcher) = runTest(dispatcher) {
      val events = MutableSharedFlow<GalleriesEvent>(extraBufferCapacity = 1)
      val repository = FakeGalleryRepository().apply { setExceptionType(ExceptionType.IO) }
      val galleryConverter = GalleryConverter(MediaConverter(), AccountImageUrlGenerator())
      val presenter = GalleriesPresenterImpl(repository, galleryConverter)
      moleculeFlow(RecompositionClock.Immediate) { presenter.present(events) }
        .test {
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), true, null))
          dispatcher.scheduler.advanceUntilIdle()
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), false, "IO Exception"))
          ensureAllEventsConsumed()
          cancel()
        }
    }

    @Test
    fun `Loading - Run exception - Crash`(dispatcher: TestDispatcher) = runTest(dispatcher) {
      val events = MutableSharedFlow<GalleriesEvent>(extraBufferCapacity = 1)
      val repository = FakeGalleryRepository().apply { setExceptionType(ExceptionType.Runtime) }
      val galleryConverter = GalleryConverter(MediaConverter(), AccountImageUrlGenerator())
      val presenter = GalleriesPresenterImpl(repository, galleryConverter)
      try {
        moleculeFlow(RecompositionClock.Immediate) { presenter.present(events) }
          .test {
            assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), true, null))
            dispatcher.scheduler.advanceUntilIdle()
          }
      } catch (exception: TestRuntimeException) {
        assertThat(exception).isInstanceOf(TestRuntimeException::class.java)
      }
    }

    @Test
    fun `Loading - IO Error - Retry`(dispatcher: TestDispatcher) = runTest(dispatcher) {
      val events = MutableSharedFlow<GalleriesEvent>(extraBufferCapacity = 1)
      val repository = FakeGalleryRepository().apply { setExceptionType(ExceptionType.IO) }
      val galleries = listOf(galleryWithMedia1, galleryWithMedia2)
      val galleryConverter = GalleryConverter(MediaConverter(), AccountImageUrlGenerator())
      val galleryItems = galleries.mapNotNull { galleryConverter.toGalleryListItem(it) }
      val presenter = GalleriesPresenterImpl(repository, galleryConverter)
      moleculeFlow(RecompositionClock.Immediate) { presenter.present(events) }
        .test {
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), true, null))
          dispatcher.scheduler.advanceUntilIdle()
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), false, "IO Exception"))
          repository.setExceptionType(ExceptionType.None)
          repository.setGalleries(galleries)
          events.tryEmit(GalleriesEvent.Retry)
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), false, "IO Exception"))
          dispatcher.scheduler.advanceUntilIdle()
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(emptyList(), true, null))
          dispatcher.scheduler.advanceUntilIdle()
          assertThat(awaitItem()).isEqualTo(GalleriesScreen(galleryItems, false, null))
          ensureAllEventsConsumed()
          cancel()
        }
    }
  }
}
