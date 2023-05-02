package de.nik1q.wkonverter.database

import android.content.Context
import androidx.room.*



import de.nik1q.wkonverter.models.ExchangeRates


@Database(entities = [ExchangeRateEntity::class], version = 1)
abstract class ExchangeRatesDatabase : RoomDatabase() {

    abstract fun exchangeRatesDao(): ExchangeRatesDao

    companion object {
        private var instance: ExchangeRatesDatabase? = null

        fun getInstance(context: Context): ExchangeRatesDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    ExchangeRatesDatabase::class.java,
                    "exchange_rates_database"
                ).build().also { instance = it }
            }
        }
    }
}
