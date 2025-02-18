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
import milori.junis.weather.di.modules.DataStoreModule
import milori.junis.weather.data.model.unit.Unit
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

private const val UNIT_ID = 2

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
}