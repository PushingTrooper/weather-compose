package milori.junis.weather.data.helpers

import milori.junis.weather.data.model.ApiGenericResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<ApiGenericResponse<S>, Call<NetworkResponse<ApiGenericResponse<S>, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<ApiGenericResponse<S>>): Call<NetworkResponse<ApiGenericResponse<S>, E>> {
        return NetworkResponseCall(call, errorBodyConverter)
    }
}