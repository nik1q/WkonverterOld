package de.nik1q.wkonverter

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import de.nik1q.wkonverter.databinding.ActivityMainBinding
import de.nik1q.wkonverter.models.LastUpdatedHelper
import de.nik1q.wkonverter.repository.ExchangeRatesRepository
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
        // pressing "los" buttton
        binding.btGetKurs.setOnClickListener {
            repository.getExchangeRates("EUR") { exchangeRates, error ->
                if (exchangeRates != null) {
                    val base = exchangeRates.base
                    val rates = exchangeRates.exchange_rates
                    val lastUpdated = exchangeRates.last_updated
                    // processing date into a log
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

