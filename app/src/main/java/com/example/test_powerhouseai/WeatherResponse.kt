package com.example.test_powerhouseai

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class WeatherResponse(
    val coord: Coord = Coord(),
    val weather: List<Weather> = emptyList(),
    val base: String = "",
    val main: Main = Main(),
    val visibility: Int = 0,
    val wind: Wind = Wind(),
    val rain: Rain = Rain(),
    val clouds: Clouds = Clouds(),
    val dt: String = "",
    val sys: Sys = Sys(),
    val timezone: Int = 0,
    @PrimaryKey
    val name: String = "",
    val message: String = "",
    val cod: Int = 0
) {
    data class Coord(
        val lat: String = "0.0",
        val long: String = "0.0"
    )

    data class Weather(
        val id: Int = 0,
        val main: String = "",
        val description: String = "",
        val icon: String = ""
    )

    data class Main(
        val temp: String = "0",
        @SerializedName("feels_like")
        val feelsLike: String = "0",
        @SerializedName("temp_min")
        val tempMin: String = "0",
        @SerializedName("temp_max")
        val tempMax: String = "0",
        val pressure: Int = 0,
        val humidity: Int = 0,
        @SerializedName("sea_level")
        val seaLevel: Int = 0,
        @SerializedName("grnd_level")
        val groundLevel: Int = 0
    )

    data class Wind(
        val speed: String = "0",
        val deg: Int = 0,
        val gust: String = "0",
    )

    data class Rain(
        @SerializedName("1h")
        val oneHour: String = ""
    )

    data class Clouds(
        val all: Int = 0,
    )

    data class Sys(
        val type: Int = 0,
        val id: Int= 0,
        val country: String = "",
        val sunrise: Int = 0,
        val sunset: Int = 0
    )
}
