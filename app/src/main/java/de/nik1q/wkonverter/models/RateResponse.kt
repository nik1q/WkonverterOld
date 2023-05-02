package de.nik1q.wkonverter.models

import de.nik1q.wkonverter.database.ExchangeRateEntity

data class RateResponse(
    val base: String,
    val exchange_rate_values: ExchangeRateEntity,
    val last_updated: Int
)


