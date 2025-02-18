package milori.junis.weather

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import milori.junis.weather.data.WeatherRepository
import milori.junis.weather.data.WeatherService
import milori.junis.weather.data.helpers.NetworkResponse
import milori.junis.weather.data.model.unit.DEFAULT_UNIT
import milori.junis.weather.di.modules.DataStoreModule
import milori.junis.weather.ui.screens.weather.WeatherViewModel
import milori.junis.weather.utils.LatAndLong
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.UseConstructor
import org.mockito.kotlin.mock

val tiranaLatAndLong = LatAndLong(19.1, 21.9)

@RunWith(MockitoJUnitRunner::class)
@HiltAndroidTest
class WeatherViewModelTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    val dispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var weatherService: WeatherService

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockContext = ApplicationProvider.getApplicationContext()
        hiltRule.inject()
        weatherRepository = mock(
            useConstructor = UseConstructor.withArguments(
                weatherService, DataStoreModule.provideProtoDataStore(mockContext)
            )
        )
        weatherViewModel = WeatherViewModel(weatherRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun simpleTest() = runTest {
        runBlocking {
            `when`(weatherRepository.getUserSelectedUnit()).thenReturn(DEFAULT_UNIT)
            `when`(
                weatherRepository.getOnlineWeather(
                    tiranaLatAndLong.latitude,
                    tiranaLatAndLong.longitude
                )
            ).thenReturn(NetworkResponse.Success(TEST_WEATHER_DATA))
            weatherViewModel.getWeatherFromLocation(tiranaLatAndLong)
            delay(1000L)
            assertEquals(TEST_WEATHER_DATA.name, weatherViewModel.city.value)
        }
    }
}