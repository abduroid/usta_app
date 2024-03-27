package com.example.ustaapp.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class TokenPairSerializer @Inject constructor() : Serializer<Tokenpair> {

    // TODO we can hardcode the token here for testing
    override val defaultValue: Tokenpair = Tokenpair.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Tokenpair =
        try {
            Tokenpair.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: Tokenpair, output: OutputStream) {
        t.writeTo(output)
    }
}
