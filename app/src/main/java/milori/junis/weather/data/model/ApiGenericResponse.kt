package milori.junis.weather.data.model

data class ApiGenericResponse<T>(
    val data: T,
    val errorMessage: Any,
    val infoMessage: Any,
    val isSuccessful: Boolean,
    val statusCode: Int,
    val validationErrors: Any
)