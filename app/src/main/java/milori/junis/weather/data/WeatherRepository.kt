package milori.junis.weather.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import milori.junis.weather.BuildConfig
import retrofit2.http.Query
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val dataStore: DataStore<Preferences>
) {
    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ) = weatherService.getCurrentWeather(latitude, longitude)

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ) = weatherService.getCurrentWeather(latitude, longitude)

    suspend fun saveUserSelectedUnit(
        unitId: Int
    ) {
        dataStore.edit {  }
    }
}