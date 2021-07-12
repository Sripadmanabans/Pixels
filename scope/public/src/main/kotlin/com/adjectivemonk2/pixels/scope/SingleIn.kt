package com.adjectivemonk2.pixels.scope

import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
@Retention(AnnotationRetention.RUNTIME)
public annotation class SingleIn(public val clazz: KClass<*>)
