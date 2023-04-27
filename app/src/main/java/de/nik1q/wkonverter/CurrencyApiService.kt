package de.nik1q.wkonverter

import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.annotations.SerializedName
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CurrencyApiService {
    @GET("v1/live")
    suspend fun getExchangeRates(
        @Query("api_key") apiKey: String,
        @Query("base") base: String,
        @Query("target") target: String
    ): ExchangeRatesResponse
}

data class ExchangeRatesResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("base")
    val base: String,
    @SerializedName("rates")
    val rates: Map<String, Double>
)

object RetrofitInstance {
    private const val BASE_URL = "https://exchange-rates.abstractapi.com/"

    fun getRetrofitInstance(): Retrofit {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}

