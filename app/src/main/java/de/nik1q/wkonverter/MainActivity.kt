package de.nik1q.wkonverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.nik1q.wkonverter.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://app.abstractapi.com/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ExchangeRatesApiService::class.java)
    }
}


