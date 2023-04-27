package de.nik1q.wkonverter

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



interface ExchangeRatesApiService {
    @GET("/exchange-rates")
    fun getExchangeRates(
        @Query("api_key") apiKey: String,
        @Query("base_currency") baseCurrency: String,
        @Query("target_currencies") targetCurrencies: String
    ): Call<Map<String, Map<String, Double>>>
}

class CurrencyApiService {

}