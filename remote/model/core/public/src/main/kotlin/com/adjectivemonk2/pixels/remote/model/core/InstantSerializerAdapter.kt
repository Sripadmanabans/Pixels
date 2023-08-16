package com.adjectivemonk2.pixels.remote.model.core

import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object InstantSerializerAdapter : KSerializer<Instant> {
  override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
    serialName = "kotlinx.datetime.Instant",
    kind = PrimitiveKind.LONG,
  )

  override fun deserialize(decoder: Decoder): Instant {
    return Instant.fromEpochMilliseconds(decoder.decodeLong())
  }

  override fun serialize(encoder: Encoder, value: Instant) {
    return encoder.encodeLong(value.toEpochMilliseconds())
  }
}
