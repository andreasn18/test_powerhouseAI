package com.example.test_powerhouseai

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@ProvidedTypeConverter
class WeatherTypeConverter {
    @TypeConverter
    fun fromCoord(coord: WeatherResponse.Coord): String {
        return Gson().toJson(coord)
    }

    @TypeConverter
    fun toCoord(coord: String): WeatherResponse.Coord {
        return Gson().fromJson(coord, WeatherResponse.Coord::class.java)
    }

    @TypeConverter
    fun fromWeather(weathers: List<WeatherResponse.Weather>): String {
        return Gson().toJson(weathers)
    }

    @TypeConverter
    fun toWeather(weather: String): List<WeatherResponse.Weather> {
        val listType = object : TypeToken<List<WeatherResponse.Weather>>() {}.type
        return Gson().fromJson(weather, listType)
    }

    @TypeConverter
    fun fromMain(main: WeatherResponse.Main): String {
        return Gson().toJson(main)
    }

    @TypeConverter
    fun toMain(main: String): WeatherResponse.Main {
        return Gson().fromJson(main, WeatherResponse.Main::class.java)
    }

    @TypeConverter
    fun fromWind(wind: WeatherResponse.Wind): String {
        return Gson().toJson(wind)
    }

    @TypeConverter
    fun toWind(wind: String): WeatherResponse.Wind {
        return Gson().fromJson(wind, WeatherResponse.Wind::class.java)
    }

    @TypeConverter
    fun fromRain(rain: WeatherResponse.Rain): String {
        return Gson().toJson(rain)
    }

    @TypeConverter
    fun toRain(rain: String): WeatherResponse.Rain {
        return Gson().fromJson(rain, WeatherResponse.Rain::class.java)
    }

    @TypeConverter
    fun fromClouds(clouds: WeatherResponse.Clouds): String {
        return Gson().toJson(clouds)
    }

    @TypeConverter
    fun toClouds(clouds: String): WeatherResponse.Clouds {
        return Gson().fromJson(clouds, WeatherResponse.Clouds::class.java)
    }

    @TypeConverter
    fun fromSys(sys: WeatherResponse.Sys): String {
        return Gson().toJson(sys)
    }

    @TypeConverter
    fun toSys(sys: String): WeatherResponse.Sys {
        return Gson().fromJson(sys, WeatherResponse.Sys::class.java)
    }
}