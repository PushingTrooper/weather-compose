package milori.junis.weather.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import milori.junis.weather.data.data_store.AppSettings
import milori.junis.weather.data.data_store.AppSettingsSerializable
import javax.inject.Singleton

private const val APP_SETTINGS_NAME = "app_settings"
private const val DATA_STORE_FILE_NAME = "app_settings.pb"

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideProtoDataStore(@ApplicationContext appContext: Context): DataStore<AppSettings> {
        return DataStoreFactory.create(
            serializer = AppSettingsSerializable,
            produceFile = { appContext.dataStoreFile(DATA_STORE_FILE_NAME) },
            corruptionHandler = null,
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        )
    }
}