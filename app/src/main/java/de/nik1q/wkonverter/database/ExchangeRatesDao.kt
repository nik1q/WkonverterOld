package de.nik1q.wkonverter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.nik1q.wkonverter.models.ExchangeRates

@Dao
interface ExchangeRatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(exchangeRates: ExchangeRateEntity)

    @Delete
    suspend fun delete(exchangeRates: ExchangeRateEntity)

    @Query("SELECT * FROM exchange_rates WHERE base = :base")
    fun getExchangeRatesLiveData(base: String): LiveData<ExchangeRateEntity>

}