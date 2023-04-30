package de.nik1q.wkonverter

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import de.nik1q.wkonverter.databinding.ActivityMainBinding
import de.nik1q.wkonverter.repository.ExchangeRatesRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.FileInputStream
import java.util.Properties
import de.nik1q.wkonverter.api.ApiClient
import de.nik1q.wkonverter.models.ExchangeRates
import de.nik1q.wkonverter.models.LastUpdatedHelper
import de.nik1q.wkonverter.models.RateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiKey = "4a88a5ab129e44abb6af1cc93d15f594"
    private val repository = ExchangeRatesRepository(apiKey)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btGetKurs.setOnClickListener {
            repository.getExchangeRates("EUR") { exchangeRates, error ->
                if (exchangeRates != null) {
                    // Делаем что-то с полученными данными, например, выводим в TextView
                    val base = exchangeRates.base
                    val rates = exchangeRates.exchange_rates
                    val lastUpdated = exchangeRates.last_updated
                    // обработка данных
                    Log.d("Exchange Rates", "Base currency: $base")
                    Log.d("Exchange Rates", "Exchange rates: $rates")
                    Log.d("Exchange Rates", "Last updated: $lastUpdated")
                    //last update Time
                    val dateString = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()).format(Date())
                    LastUpdatedHelper.updateLastUpdated(dateString, binding.txLastUpd)
                } else {
                    Toast.makeText(this, "Error: ${error?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

