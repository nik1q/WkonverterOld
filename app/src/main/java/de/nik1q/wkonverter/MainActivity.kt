package de.nik1q.wkonverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import de.nik1q.wkonverter.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.FileInputStream
import java.util.Properties


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    private lateinit var currencyApiService: CurrencyApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currencyApiService = RetrofitInstance.getRetrofitInstance().create(CurrencyApiService::class.java)

        // Api
        val apikey: String
        val props = Properties()
        try {
            props.load(FileInputStream("local.properties"))
            apikey = props.getProperty("API_KEY")
        } catch (e: Exception) {
            throw RuntimeException("API key not found", e)
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = currencyApiService.getExchangeRates(
                    apiKey = "API_KEY",
                    base = "USD",
                    target = "EUR,CAD"
                )

                withContext(Dispatchers.Main) {
                    // date
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }
}


