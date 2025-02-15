package milori.junis.weather.navigation

enum class WeatherScreens {
    WeatherScreen,
    SettingsScreen;

    companion object {
        fun fromRoute(route: String?): WeatherScreens = when (route?.substringBefore("/")) {
            WeatherScreen.name -> WeatherScreen
            SettingsScreen.name -> SettingsScreen
            else -> WeatherScreen
        }
    }
}