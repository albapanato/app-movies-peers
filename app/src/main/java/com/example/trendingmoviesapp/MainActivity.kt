package com.example.trendingmoviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trendingmoviesapp.ui.theme.TrendingMoviesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val apiKey = sharedPreferences.getString("API_KEY", null)

            if (apiKey == null) {
                ApiKeyScreen { key ->
                    with(sharedPreferences.edit()) {
                        putString("API_KEY", key)
                        apply()
                    }
                }
            } else {
                // Navegar a la pantalla principal
                MainScreen(apiKey)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrendingMoviesAppTheme {
        Greeting("Android")
    }
}