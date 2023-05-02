package de.nik1q.wkonverter.repository

import de.nik1q.wkonverter.api.ApiClient
import de.nik1q.wkonverter.database.ExchangeRateEntity
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
    fun getExchangeRates(base: String, onResult: (ExchangeRateEntity?, Throwable?) -> Unit) {
        apiService.getExchangeRates(apiKey, base).enqueue(object : Callback<RateResponse> {
            override fun onResponse(call: Call<RateResponse>, response: Response<RateResponse>) {
                if (response.isSuccessful) {
                    val rateResponse = response.body()
                    if (rateResponse != null) {
                        val exchangeRates = ExchangeRateEntity(
                            base = rateResponse.base,
                            ARS = rateResponse.exchange_rate_values.ARS,
                            AUD = rateResponse.exchange_rate_values.AUD,
                            BCH = rateResponse.exchange_rate_values.BCH,
                            BGN = rateResponse.exchange_rate_values.BGN,
                            BNB = rateResponse.exchange_rate_values.BNB,
                            BRL = rateResponse.exchange_rate_values.BRL,
                            BTC = rateResponse.exchange_rate_values.BTC,
                            CAD = rateResponse.exchange_rate_values.CAD,
                            CHF = rateResponse.exchange_rate_values.CHF,
                            CNY = rateResponse.exchange_rate_values.CNY,
                            CZK = rateResponse.exchange_rate_values.CZK,
                            DKK = rateResponse.exchange_rate_values.DKK,
                            DOGE = rateResponse.exchange_rate_values.DOGE,
                            DZD = rateResponse.exchange_rate_values.DZD,
                            ETH = rateResponse.exchange_rate_values.ETH,
                            EUR = rateResponse.exchange_rate_values.EUR,
                            GBP = rateResponse.exchange_rate_values.GBP,
                            HKD = rateResponse.exchange_rate_values.HKD,
                            HRK = rateResponse.exchange_rate_values.HRK,
                            HUF = rateResponse.exchange_rate_values.HUF,
                            IDR = rateResponse.exchange_rate_values.IDR,
                            ILS = rateResponse.exchange_rate_values.ILS,
                            INR = rateResponse.exchange_rate_values.INR,
                            ISK = rateResponse.exchange_rate_values.ISK,
                            JPY = rateResponse.exchange_rate_values.JPY,
                            KRW = rateResponse.exchange_rate_values.KRW,
                            LTC = rateResponse.exchange_rate_values.LTC,
                            MAD = rateResponse.exchange_rate_values.MAD,
                            MXN = rateResponse.exchange_rate_values.MXN,
                            MYR = rateResponse.exchange_rate_values.MYR,
                            NOK = rateResponse.exchange_rate_values.NOK,
                            NZD = rateResponse.exchange_rate_values.NZD,
                            PHP = rateResponse.exchange_rate_values.PHP,
                            PLN = rateResponse.exchange_rate_values.PLN,
                            RON = rateResponse.exchange_rate_values.RON,
                            RUB = rateResponse.exchange_rate_values.RUB,
                            SEK = rateResponse.exchange_rate_values.SEK,
                            SGD = rateResponse.exchange_rate_values.SGD,
                            THB = rateResponse.exchange_rate_values.THB,
                            TRY = rateResponse.exchange_rate_values.TRY,
                            TWD = rateResponse.exchange_rate_values.TWD,
                            USD = rateResponse.exchange_rate_values.USD,
                            XRP = rateResponse.exchange_rate_values.XRP,
                            ZAR = rateResponse.exchange_rate_values.ZAR,

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



