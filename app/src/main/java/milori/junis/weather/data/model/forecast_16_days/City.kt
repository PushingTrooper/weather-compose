package milori.junis.weather.data.model.forecast_16_days


import com.google.gson.annotations.SerializedName

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int
)