package milori.junis.weather.utils

import milori.junis.weather.R

fun getIconResFromWeatherCode(weatherCode: Int) = if (weatherCode == 800) {
    R.drawable.clear_day
} else {
    when (weatherCode / 100) {
        2 -> R.drawable.thunderstorm
        3 -> R.drawable.rainy_light
        5 -> R.drawable.rainy
        6 -> R.drawable.weather_snowy
        7 -> R.drawable.mist
        8 -> R.drawable.cloud
        else -> R.drawable.clear_day
    }
}