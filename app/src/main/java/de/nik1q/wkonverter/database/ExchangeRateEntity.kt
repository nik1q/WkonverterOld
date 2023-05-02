package de.nik1q.wkonverter.database

import androidx.room.*

@Entity(tableName = "exchange_rates")
data class ExchangeRateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val base: String,
    val exchangeRate: Double,
    val currency: String,
    val lastUpdated: String
)