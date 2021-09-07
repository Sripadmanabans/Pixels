package com.adjectivemonk2.pixels.network.gallery.fake

import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.adjectivemonk2.pixels.network.gallery.GalleryRepository
import com.adjectivemonk2.pixels.network.gallery.Section
import com.adjectivemonk2.pixels.network.gallery.Sort
import com.adjectivemonk2.pixels.network.gallery.Window
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import kotlin.time.Duration

public class FakeGalleryRepository : GalleryRepository {

  private var exceptionType = ExceptionType.None

  public fun setExceptionType(type: ExceptionType) {
    exceptionType = type
  }

  override fun getGallery(
    section: Section,
    sort: Sort,
    window: Window,
    page: Int
  ): Flow<List<Gallery>> {
    return flow {
      delay(Duration.seconds(ONE))
      when (exceptionType) {
        ExceptionType.IO -> throw IOException("IO Exception")
        ExceptionType.Runtime -> throw TestRuntimeException("Runtime exception")
        ExceptionType.None -> emit(emptyList())
      }
    }
  }

  private companion object {
    const val ONE = 1L
  }
}

public class TestRuntimeException(
  override val message: String = "Runtime exception",
) : RuntimeException(message)

public enum class ExceptionType {
  IO, Runtime, None
}
