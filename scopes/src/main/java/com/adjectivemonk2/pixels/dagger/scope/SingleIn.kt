package com.adjectivemonk2.pixels.dagger.scope

import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
@Retention(AnnotationRetention.RUNTIME)
public annotation class SingleIn(val clazz: KClass<*>)
