package de.nik1q.wkonverter.repository

import de.nik1q.wkonverter.api.ApiClient
import de.nik1q.wkonverter.database.ExchangeRatesDao
import de.nik1q.wkonverter.models.ExchangeRates
import de.nik1q.wkonverter.models.RateResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ExchangeRatesRepository(private val apiKey: String, private val exchangeRatesDao: ExchangeRatesDao) {
    private val apiService = ApiClient().apiService

    fun getExchangeRates(base: String, onResult: (ExchangeRates?, Throwable?) -> Unit) {
        apiService.getExchangeRates(apiKey, base).enqueue(object : Callback<RateResponse> {
            override fun onResponse(call: Call<RateResponse>, response: Response<RateResponse>) {
                if (response.isSuccessful) {
                    val rateResponse = response.body()
                    if (rateResponse != null) {
                        val exchangeRates = ExchangeRates(
                            base = rateResponse.base,
                            exchange_rates = rateResponse.exchange_rates.exchange_rates,
                            last_updated = rateResponse.last_updated.toString()
                        )
                        GlobalScope.launch {
                            exchangeRatesDao.insertOrUpdate(exchangeRates)
                        }
                        onResult(exchangeRates, null)
                    } else {
                        onResult(null, Exception("Error: Empty response body"))
                    }
                } else {
                    onResult(null, Exception("Error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<RateResponse>, t: Throwable) {
                onResult(null, t)
            }
        })
    }
}


