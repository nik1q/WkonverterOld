package de.nik1q.wkonverter.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRates(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val base: String,
    val exchange_rates: Map<String, Any>,
    val last_updated: String
)
