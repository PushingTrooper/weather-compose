package milori.junis.weather.data.model.forecast_16_days


import com.google.gson.annotations.SerializedName

data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)