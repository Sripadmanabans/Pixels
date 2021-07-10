package com.adjectivemonk2.lifecycle

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
public annotation class ViewModelKey(public val clazz: KClass<out ViewModel>)
