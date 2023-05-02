package de.nik1q.wkonverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.nik1q.wkonverter.database.ExchangeRateEntity
import de.nik1q.wkonverter.database.ExchangeRatesDao
import de.nik1q.wkonverter.models.ExchangeRates
import de.nik1q.wkonverter.repository.ExchangeRatesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class ExchangeRatesViewModel(private val exchangeRatesDao: ExchangeRatesDao, private val apiKey: String) : ViewModel() {

    private val repository = ExchangeRatesRepository(apiKey, exchangeRatesDao)

    fun getExchangeRates(base: String): LiveData<ExchangeRateEntity> {
        refreshExchangeRates(base)
        return exchangeRatesDao.getExchangeRatesLiveData(base)
    }

}

    private fun refreshExchangeRates(base: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getExchangeRates(base) { _, _ -> }
        }
    }
}
