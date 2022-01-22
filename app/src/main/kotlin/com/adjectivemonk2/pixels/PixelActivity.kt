package com.adjectivemonk2.pixels

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import com.adjectivemonk2.pixels.theme.PixelsTheme
import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesWorkflow
import com.squareup.workflow1.WorkflowInterceptor
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.WorkflowRendering
import com.squareup.workflow1.ui.compose.renderAsState
import javax.inject.Inject

typealias WorkflowInterceptors = List<@JvmSuppressWildcards WorkflowInterceptor>

class PixelActivity : FragmentActivity() {

  @Inject internal lateinit var galleriesWorkflow: GalleriesWorkflow
  @Inject internal lateinit var viewEnvironment: ViewEnvironment
  @Inject internal lateinit var workflowInterceptors: WorkflowInterceptors

  override fun onCreate(savedInstanceState: Bundle?) {
    appComponent.activityComponent().injectInTo(this)
    super.onCreate(savedInstanceState)
    setContent {
      PixelsTheme {
        val rendering by galleriesWorkflow.renderAsState(
          props = Unit,
          interceptors = workflowInterceptors,
          onOutput = {},
        )
        WorkflowRendering(rendering = rendering, viewEnvironment = viewEnvironment)
      }
    }
  }
}
