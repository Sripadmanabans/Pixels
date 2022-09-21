package com.adjectivemonk2.pixels.di

import androidx.fragment.app.FragmentActivity
import com.adjectivemonk2.pixels.scope.ActivityScope
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo

@SingleIn(ActivityScope::class)
@ContributesSubcomponent(scope = ActivityScope::class, parentScope = AppScope::class)
public interface ActivityComponent {

  public val activity: FragmentActivity

  @ContributesSubcomponent.Factory
  public interface Factory {
    public fun create(): ActivityComponent
  }

  @ContributesTo(AppScope::class)
  public interface ParentComponent {
    public val activityComponentFactory: Factory
  }
}
