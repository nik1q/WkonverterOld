package de.nik1q.wkonverter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.nik1q.wkonverter.models.ExchangeRates

@Dao
interface ExchangeRatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(exchangeRates: ExchangeRates)

    @Delete
    suspend fun delete(exchangeRates: ExchangeRates)

    @Query("SELECT * FROM exchange_rates WHERE base = :base")
    fun getExchangeRatesLiveData(base: String): LiveData<ExchangeRates>

}
