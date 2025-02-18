The goal of this app is to fetch data from [OpenWeather](https://openweathermap.org/) according to the user's location display it and save it for offline use.

## Clean architecture with 3 main modules
- Data (for database, API and preferences code)
  - WeatherService, DataStore, WeatherRepository
- Domain
  - ViewModels
- AndroidApp
  - Composables

## Libraries in use
- AndroidX
- Compose
- DataStore
- Hilt
- HiltCompose
- junit
- Mockito
- KotlinX
- Retrofit
- PlayServicesLocation

## Tests
- [Mockito](https://site.mockito.org/) library 
- Unit tests
    
## The apps architecture
The architecture used in the app is MVVM.
The file structure is as follows:
- data
  - data_store(offline data storage)
  - network helpers(generic files, adapter and factory)
  - model
  - repository
  - service
- screen navigation
- ui
  - screens(with their respected viewmodels)
  - theme

The application is structured across three distinct layers, as described previously.
The Data Layer is responsible for managing the offline database, utilizing DataStore, as well as interacting with the online OpenWeather API. Within this layer, we retrieve data, convert it from JSON to Kotlin objects via a converter factory, and access both the local database and the repository, which acts as an intermediary between the two.
The ViewModels, which encapsulate the business logic, hold a reference exclusively to the repository. The ViewModel determines the source of the data, manages the data flow, handles saving and mapping, and performs necessary transformations. Importantly, the ViewModel does not communicate directly with the views. Instead, the views maintain a reference to their respective ViewModel and "subscribe" to the MutableState variables exposed within it.
This architecture ensures a clear separation of concerns, decoupling business logic from UI logic. The views are only concerned with the type of data they need to display and the data they need to receive. This separation simplifies testing and enhances maintainability. Additionally, by leveraging Dependency Injection with Hilt, we reduce the risk of memory leaks and further facilitate testability, creating a more robust and scalable application environment.


# Getting started

1. Download this repository extract and open the template folder on Android Studio
2. Add your own [OpenWeatherApiKey](https://home.openweathermap.org/api_keys) to the local.gradle file
3. Make sure to use Java 11
4. Run the app
5. Provide location services
6. Ready to Use
