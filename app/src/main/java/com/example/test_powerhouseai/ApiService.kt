package com.example.test_powerhouseai

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("units") unit: String,
        @Query("appid") apiKey: String
    ): WeatherResponse

    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("q", encoded = true) query: String,
        @Query("units") unit: String,
        @Query("appid") apiKey: String
    ): WeatherResponse
}