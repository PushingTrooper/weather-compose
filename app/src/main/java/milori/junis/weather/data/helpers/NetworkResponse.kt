package milori.junis.weather.data.helpers

import milori.junis.weather.data.model.APIError
import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any?> {
    /**
     * Success response with body
     */
    data class NullableSuccess<T : Any>(val body: T?) : NetworkResponse<T, Nothing>()
    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    /**
     * Failure response with body
     */
    data class ApiError<U : Any?>(val body: U?, val code: Int) : NetworkResponse<Nothing, U>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable?, val code: Int) : NetworkResponse<Nothing, Nothing>()
    data object Loading : NetworkResponse<Nothing, Nothing>()
}

typealias GenericResponse<S> = NetworkResponse<S, APIError>