package milori.junis.weather.data.model

data class APIError(
    val code: Int?,
    val message: String,
    val extraInfo: String?,
    val stacktrace: List<String>?
)