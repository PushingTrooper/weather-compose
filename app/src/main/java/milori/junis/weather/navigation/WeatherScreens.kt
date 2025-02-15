package milori.junis.weather.navigation

enum class WeatherScreens {
    WeatherScreen;

    companion object {
        fun fromRoute(route: String?): WeatherScreens = when (route?.substringBefore("/")) {
            WeatherScreen.name -> WeatherScreen
            else -> WeatherScreen
        }
    }
}