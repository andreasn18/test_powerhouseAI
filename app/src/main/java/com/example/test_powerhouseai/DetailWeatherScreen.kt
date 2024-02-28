package com.example.test_powerhouseai

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import java.util.Locale

@Composable
fun DetailWeatherScreen(
    viewModel: WeatherViewModel,
    navController: NavHostController,
    city: String?
) {
    viewModel.getSpecificWeather(city ?: "Indonesia")
    val weather = viewModel.weather.collectAsState().value
    val locWeather = weather.weather.firstOrNull() ?: WeatherResponse.Weather()
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = weather.name, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = viewModel.weatherIconUri,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "${locWeather.main} ${
                        weather.main.tempMin.toDouble().toInt()
                    }\u00B0 / ${weather.main.tempMax.toDouble().toInt()}\u00B0",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(text = locWeather.description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }, modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Feels Like: ${weather.main.feelsLike.toDouble().toInt()}\u00B0")
        Text(text = "Humidity: ${weather.main.humidity}")
        Text(text = "Ground Level: ${weather.main.groundLevel}")
        Text(text = "Sea Level: ${weather.main.seaLevel}")
        Text(text = "Pressure: ${weather.main.pressure}")
        Text(text = "Visibility: ${weather.visibility} m")
    }
}