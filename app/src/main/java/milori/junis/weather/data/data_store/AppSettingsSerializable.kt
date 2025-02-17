package milori.junis.weather.data.data_store

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object AppSettingsSerializable: Serializer<AppSettings> {
    override val defaultValue: AppSettings
        get() = AppSettings()

    override suspend fun readFrom(input: InputStream): AppSettings {
        return try {
            Json.decodeFromString(
                AppSettings.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: AppSettings,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(
                AppSettings.serializer(),
                t
            ).encodeToByteArray()
        )
    }
}