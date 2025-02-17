package milori.junis.weather.ui.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import milori.junis.weather.R
import milori.junis.weather.data.WeatherRepository
import milori.junis.weather.data.model.unit.DEFAULT_UNIT
import milori.junis.weather.data.model.unit.Unit
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val userSelectedUnit = mutableStateOf(DEFAULT_UNIT)

    init {
        getUserSelectedUnit()
    }

    fun getUserSelectedUnit() {
        viewModelScope.launch {
            userSelectedUnit.value = repository.getUserSelectedUnit()
        }
    }

    fun setSelectedUnit(unit: Unit) {
        viewModelScope.launch {
            repository.saveUserSelectedUnit(unit)
            userSelectedUnit.value = unit
        }
    }
}