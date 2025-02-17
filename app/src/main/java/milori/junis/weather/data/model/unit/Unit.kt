package milori.junis.weather.data.model.unit

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import milori.junis.weather.R

@Serializable
data class Unit(
    val id: Int,
    @StringRes val name: Int
)

val DEFAULT_UNIT = Unit(1, R.string.metric)