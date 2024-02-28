package com.example.test_powerhouseai

import android.content.Context
import android.util.Log
import androidx.room.Room

class WeatherRepository(context: Context) {
    private val apiService = RetrofitInstance.api
    private val db = Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_database")
//        .addTypeConverter(WeatherTypeConverter::class.java)
        .build()
    val weatherDao = db.weatherDao()

    suspend fun loadCurrentWeather(lat: String, long: String): WeatherResponse? {
        return try {
            val hit = apiService.getCurrentWeather(lat, long, "metric", WEATHER_API_KEY)
            hit
        } catch (e: Exception) {
            Log.e("Retrofit Exception", e.message ?: "No Message")
            null
        }
    }

    suspend fun loadWeatherByLocation(location: String): WeatherResponse? {
        return try {
            val hit = apiService.getWeatherByLocation(location, "metric", WEATHER_API_KEY)
            hit
        } catch (e: Exception) {
            Log.e("Retrofit Exception", e.message ?: "No Message")
            null
        }
    }

    //Local
    suspend fun insertWeathers(vararg weathers: WeatherResponse) {
        weatherDao.insertAll(*weathers)
    }

    suspend fun getAllWeathers(): List<WeatherResponse> {
        return weatherDao.getAll()
    }

    companion object {
        const val WEATHER_API_KEY = "3a3d7d323720146ad58d30530ddea3b0"
    }
}