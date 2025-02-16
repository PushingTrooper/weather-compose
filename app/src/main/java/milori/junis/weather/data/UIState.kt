package milori.junis.weather.data

import milori.junis.weather.data.helpers.Either
import kotlin.time.TimeMark

data class UiState(
    val showSnackBarEvent: TimeMark? = null,
    val message: Either<Int, String>? = null
)
