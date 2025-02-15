package milori.junis.weather.data

import com.google.gson.GsonBuilder
import milori.junis.weather.BuildConfig
import milori.junis.weather.data.helpers.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class WeatherServiceBuilder {
    private fun client(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            clientBuilder.addInterceptor(logging)
        }

        return clientBuilder
            .build()
    }

    private var gson = GsonBuilder()
        .setLenient()
        .create()


    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client())
            .build()
    }

    fun buildService(): WeatherService {
        return retrofit().create(WeatherService::class.java)
    }
}