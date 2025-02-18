package milori.junis.weather

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import milori.junis.weather.data.data_store.AppSettings
import milori.junis.weather.data.model.current_weather.Clouds
import milori.junis.weather.data.model.current_weather.Coord
import milori.junis.weather.data.model.current_weather.CurrentWeatherResponse
import milori.junis.weather.data.model.current_weather.Main
import milori.junis.weather.data.model.current_weather.Sys
import milori.junis.weather.data.model.current_weather.Weather
import milori.junis.weather.data.model.current_weather.Wind
import milori.junis.weather.di.modules.DataStoreModule
import milori.junis.weather.data.model.unit.Unit
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

private const val UNIT_ID = 2
val TEST_WEATHER_DATA = CurrentWeatherResponse(
    coord = Coord(lon = 19.8197, lat = 41.3234),
    weather = listOf(
        Weather(
            id = 802,
            main = "Clouds",
            description = "scattered clouds",
            icon = "03d"
        )
    ),
    base = "stations",
    main = Main(
        temp = 12.52,
        feelsLike = 11.05,
        tempMin = 12.52,
        tempMax = 12.52,
        pressure = 1019,
        humidity = 47,
        seaLevel = 1019,
        grndLevel = 982
    ),
    visibility = 10000,
    wind = Wind(speed = 3.6, deg = 300, gust = 0.0),
    rain = null,
    clouds = Clouds(all = 40),
    dt = 1739879994,
    sys = Sys(type = 1, id = 6359, country = "AL", sunrise = 1739856705, sunset = 1739895470),
    timezone = 3600,
    id = 3183875,
    name = "Tirana",
    cod = 200
)

@RunWith(MockitoJUnitRunner::class)
class DataStoreReadWriteTest {
    val testScope = TestScope()

    companion object {
        @Mock
        private lateinit var mockContext: Context
        private lateinit var dataStore: DataStore<AppSettings>

        @JvmStatic
        @BeforeClass
        fun setUp() {
            mockContext = ApplicationProvider.getApplicationContext()
            dataStore = DataStoreModule.provideProtoDataStore(mockContext)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeReadUnitOfMeasurement() = runTest {
        runBlocking {
            dataStore.updateData {
                it.copy(
                    unit = Unit(UNIT_ID, R.string.imperial)
                )
            }
        }
        runBlocking {
            assertEquals(UNIT_ID, dataStore.data.first().unit.id)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeReadCurrentWeatherData() = runTest {
        runBlocking {
            dataStore.updateData {
                it.copy(
                    currentWeatherResponse = TEST_WEATHER_DATA
                )
            }
        }
        runBlocking {
            assertEquals(TEST_WEATHER_DATA, dataStore.data.first().currentWeatherResponse)
        }
    }
}