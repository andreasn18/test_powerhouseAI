package com.example.test_powerhouseai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _weathers: MutableStateFlow<List<WeatherResponse>> = MutableStateFlow(emptyList())
    val weathers = _weathers.asStateFlow()

    private val _weather: MutableStateFlow<WeatherResponse> = MutableStateFlow(WeatherResponse())
    val weather = _weather.asStateFlow()
    var weatherIconUri by mutableStateOf("")

    var noInternetToast by mutableStateOf(false)

    private var numRetry = 0

    var lat by mutableStateOf(0.0)
    var long by mutableStateOf(0.0)
    var permissionState by mutableStateOf(false)
    var isLoading by mutableStateOf(true)
    private val otherRegion = listOf(
        "New York",
        "Singapore",
        "Mumbai",
        "Delhi",
        "Sydney",
        "Melbourne"
    )

    private fun getCurrentWeather(location: String? = null) {
        viewModelScope.launch {
            val response = if (location == null) {
                weatherRepository.loadCurrentWeather(lat.toString(), long.toString())
            } else {
                weatherRepository.loadWeatherByLocation(location)
            }
            if (response == null) {
                _weathers.value = weatherRepository.getAllWeathers()
                noInternetToast = true
            } else {
                numRetry = 0
                var sameLocation = _weathers.value.find {
                    it.name == response.name
                }
                val index = _weathers.value.indexOf(sameLocation)
                val rawWeathers = _weathers.value.toMutableList()
                if (sameLocation != null) {
                    sameLocation = sameLocation.copy(
                        coord = response.coord,
                        weather = response.weather,
                        base = response.base,
                        main = response.main,
                        visibility = response.visibility,
                        wind = response.wind,
                        rain = response.rain,
                        clouds = response.clouds,
                        dt = response.dt,
                        sys = response.sys
                    )
                    rawWeathers[index] = sameLocation
                    _weathers.value = rawWeathers
                } else {
                    rawWeathers.add(response)
                    _weathers.value = rawWeathers
                }
                weatherRepository.insertWeathers(*_weathers.value.toTypedArray())
            }
        }
    }

    fun getSpecificWeather(location: String) {
        viewModelScope.launch {
            val response = weatherRepository.loadWeatherByLocation(location) ?: WeatherResponse()
            weatherIconUri = "https://openweathermap.org/img/wn/${response.weather[0].icon}@4x.png"
            _weather.value = response
        }
    }

    fun refreshData() {
        isLoading = true
        viewModelScope.launch {
            getCurrentWeather()
            delay(1000)
            otherRegion.forEach {
                getCurrentWeather(it)
            }
            isLoading = false
        }
    }
}