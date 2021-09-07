package com.adjectivemonk2.pixels.ui.galleries.common.impl

import com.adjectivemonk2.pixels.network.gallery.fake.ExceptionType
import com.adjectivemonk2.pixels.network.gallery.fake.FakeGalleryRepository
import com.adjectivemonk2.pixels.network.gallery.fake.TestRuntimeException
import com.adjectivemonk2.pixels.testing.DispatcherParameterResolver
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen
import com.google.common.truth.Truth.assertThat
import com.squareup.workflow1.testing.launchForTestingFromStartWith
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

internal class GalleriesWorkflowImplTest {

  @Test @ExtendWith(value = [DispatcherParameterResolver::class])
  fun `When the api calls passes, we will show loading then the info screen`(dispatcher: TestCoroutineDispatcher) {
    val repository = FakeGalleryRepository()
    GalleriesWorkflowImpl(repository).launchForTestingFromStartWith(context = dispatcher) {
      dispatcher.pauseDispatcher()
      val render1 = awaitNextRendering()
      assertThat(render1).isEqualTo(GalleriesScreen.Loading)
      dispatcher.advanceUntilIdle()
      dispatcher.resumeDispatcher()
      val render2 = awaitNextRendering()
      assertThat(render2).isEqualTo(GalleriesScreen.Info(emptyList()))
    }
  }

  @Test @ExtendWith(value = [DispatcherParameterResolver::class])
  fun `When the api calls fails with IO, we will show loading then the error screen`(
    dispatcher: TestCoroutineDispatcher
  ) {
    val repository = FakeGalleryRepository().apply { setExceptionType(ExceptionType.IO) }
    GalleriesWorkflowImpl(repository).launchForTestingFromStartWith(context = dispatcher) {
      dispatcher.pauseDispatcher()
      val render1 = awaitNextRendering()
      assertThat(render1).isEqualTo(GalleriesScreen.Loading)
      dispatcher.advanceUntilIdle()
      dispatcher.resumeDispatcher()
      val render2 = awaitNextRendering()
      assertThat(render2).isEqualTo(GalleriesScreen.Error("IO Exception"))
    }
  }

  @Test @ExtendWith(value = [DispatcherParameterResolver::class])
  fun `When the api calls fails with runtime exception, we will show loading then throw the exception`(
    dispatcher: TestCoroutineDispatcher
  ) {
    assertThrows(TestRuntimeException::class.java) {
      val repository = FakeGalleryRepository().apply { setExceptionType(ExceptionType.Runtime) }
      GalleriesWorkflowImpl(repository).launchForTestingFromStartWith(context = dispatcher) {
        dispatcher.pauseDispatcher()
        val render1 = awaitNextRendering()
        assertThat(render1).isEqualTo(GalleriesScreen.Loading)
        dispatcher.advanceUntilIdle()
        dispatcher.resumeDispatcher()
      }
    }
  }
}
