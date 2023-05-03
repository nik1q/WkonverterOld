package de.nik1q.wkonverter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import de.nik1q.wkonverter.databinding.ActivityMainBinding
import de.nik1q.wkonverter.models.LastUpdatedHelper
import de.nik1q.wkonverter.viewmodel.ExchangeRatesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiKey = "4a88a5ab129e44abb6af1cc93d15f594"
    private lateinit var viewModel: ExchangeRatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Erstellen Sie das ViewModel mithilfe des ViewModelProvider
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(ExchangeRatesViewModel::class.java)

        // Wenn der "los" Button geklickt wird, verwenden Sie das ViewModel, um ExchangeRates abzurufen
        binding.btGetKurs.setOnClickListener {
            viewModel.getExchangeRates("EUR").observe(this, Observer { exchangeRates ->
                val base = exchangeRates.base
                val rates = exchangeRates
                val lastUpdated = exchangeRates.last_updated
                // processing date into a log
                Log.d("Exchange Rates", "Base currency: $base")
                Log.d("Exchange Rates", "Exchange rates: $rates")
                Log.d("Exchange Rates", "Last updated: $lastUpdated")
                //last update Time
                val dateString = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()).format(Date())
                LastUpdatedHelper.updateLastUpdated(dateString, binding.txLastUpd)
            })
        }
    }
}
