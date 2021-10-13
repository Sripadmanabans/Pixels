package com.adjectivemonk2.pixels.ui.galleries.common.impl

import com.adjectivemonk2.pixels.network.gallery.fake.ExceptionType
import com.adjectivemonk2.pixels.network.gallery.fake.FakeGalleryRepository
import com.adjectivemonk2.pixels.network.gallery.fake.TestRuntimeException
import com.adjectivemonk2.pixels.network.gallery.fake.galleryWithMedia1
import com.adjectivemonk2.pixels.network.gallery.fake.galleryWithMedia2
import com.adjectivemonk2.pixels.testing.DispatcherParameterResolver
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesWorkflow
import com.google.common.truth.Truth.assertThat
import com.squareup.workflow1.testing.launchForTestingFromStartWith
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

internal class GalleriesWorkflowImplTest {

  @DisplayName("Testing success cases")
  @Nested
  inner class Success {

    @Test @ExtendWith(value = [DispatcherParameterResolver::class])
    fun `Success with empty list, we will show loading then the empty screen`(
      dispatcher: TestCoroutineDispatcher
    ) {
      val repository = FakeGalleryRepository()
      createWorkflow(repository).launchForTestingFromStartWith(context = dispatcher) {
        dispatcher.pauseDispatcher()
        val render1 = awaitNextRendering()
        assertThat(render1).isEqualTo(GalleriesScreen.Loading)
        dispatcher.advanceUntilIdle()
        dispatcher.resumeDispatcher()
        val render2 = awaitNextRendering()
        assertThat(render2).isEqualTo(GalleriesScreen.Empty)
      }
    }

    @Test @ExtendWith(value = [DispatcherParameterResolver::class])
    fun `Success with data, we will show loading then the empty screen`(
      dispatcher: TestCoroutineDispatcher
    ) {
      val galleryConverter = GalleryConverter(MediaConverter(), AccountImageUrlGenerator())
      val galleries = listOf(galleryWithMedia1, galleryWithMedia2)
      val galleryItems = galleries.mapNotNull { galleryConverter.toGalleryListItem(it) }
      val repository = FakeGalleryRepository().apply {
        setGalleryList(galleries)
      }
      createWorkflow(
        repository,
        galleryConverter
      ).launchForTestingFromStartWith(context = dispatcher) {
        dispatcher.pauseDispatcher()
        val render1 = awaitNextRendering()
        assertThat(render1).isEqualTo(GalleriesScreen.Loading)
        dispatcher.advanceUntilIdle()
        dispatcher.resumeDispatcher()
        val render2 = awaitNextRendering()
        assertThat(render2).isEqualTo(GalleriesScreen.Info(galleryItems))
      }
    }
  }

  @DisplayName("Testing error cases")
  @Nested
  inner class Error {

    @Test @ExtendWith(value = [DispatcherParameterResolver::class])
    fun `IO exception, we will show loading then the error screen`(
      dispatcher: TestCoroutineDispatcher
    ) {
      val repository = FakeGalleryRepository().apply { setExceptionType(ExceptionType.IO) }
      createWorkflow(repository).launchForTestingFromStartWith(context = dispatcher) {
        dispatcher.pauseDispatcher()
        val render1 = awaitNextRendering()
        assertThat(render1).isEqualTo(GalleriesScreen.Loading)
        dispatcher.advanceUntilIdle()
        dispatcher.resumeDispatcher()
        val render2 = awaitNextRendering()
        assertThat(render2).isInstanceOf(GalleriesScreen.Error::class.java)
        val error = render2 as GalleriesScreen.Error
        assertThat(error.message).isEqualTo("IO Exception")
      }
    }

    @Test @ExtendWith(value = [DispatcherParameterResolver::class])
    fun `IO exception, we will show loading then the error screen with retry click restarting flow`(
      dispatcher: TestCoroutineDispatcher
    ) {
      val repository = FakeGalleryRepository().apply { setExceptionType(ExceptionType.IO) }
      createWorkflow(repository).launchForTestingFromStartWith(context = dispatcher) {
        dispatcher.pauseDispatcher()
        val render1 = awaitNextRendering()
        assertThat(render1).isEqualTo(GalleriesScreen.Loading)
        dispatcher.advanceUntilIdle()
        dispatcher.resumeDispatcher()
        val render2 = awaitNextRendering()
        assertThat(render2).isInstanceOf(GalleriesScreen.Error::class.java)
        val error = render2 as GalleriesScreen.Error
        assertThat(error.message).isEqualTo("IO Exception")
        repository.setExceptionType(ExceptionType.None)
        repository.setGalleryList(emptyList())
        error.onRetryClick()
        val render3 = awaitNextRendering()
        assertThat(render3).isEqualTo(GalleriesScreen.Loading)
        dispatcher.advanceUntilIdle()
        dispatcher.resumeDispatcher()
        val render4 = awaitNextRendering()
        assertThat(render4).isEqualTo(GalleriesScreen.Empty)
      }
    }

    @Test @ExtendWith(value = [DispatcherParameterResolver::class])
    fun `Runtime exception, we will show loading then throw the exception`(
      dispatcher: TestCoroutineDispatcher
    ) {
      assertThrows(TestRuntimeException::class.java) {
        val repository = FakeGalleryRepository().apply { setExceptionType(ExceptionType.Runtime) }
        createWorkflow(repository).launchForTestingFromStartWith(context = dispatcher) {
          dispatcher.pauseDispatcher()
          val render1 = awaitNextRendering()
          assertThat(render1).isEqualTo(GalleriesScreen.Loading)
          dispatcher.advanceUntilIdle()
          dispatcher.resumeDispatcher()
        }
      }
    }
  }

  private fun createWorkflow(
    fakeGalleryRepository: FakeGalleryRepository,
    galleryConverter: GalleryConverter = GalleryConverter(
      MediaConverter(),
      AccountImageUrlGenerator()
    ),
  ): GalleriesWorkflow {
    return GalleriesWorkflowImpl(fakeGalleryRepository, galleryConverter)
  }
}
