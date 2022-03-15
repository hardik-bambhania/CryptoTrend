package com.hardik.cryptotrend.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptotrend.common.logger.AppLogger
import com.cryptotrend.repository.Repository
import com.hardik.cryptotrend.model.CryptoCurrencyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        getCryptoData()
    }

    val currencyData = repository.getAllCryptoCurrency().flowOn(Dispatchers.IO)

    /**
     * fetch the crypto currency data from remote API
     */
    private fun getCryptoData() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                getLatestCryptoData()
                delay(com.cryptotrend.common.Constant.DATA_REFRESH_INTERVAL)
            }
        }
    }

    private suspend fun getLatestCryptoData() {
        try {
            CryptoCurrencyItem.values().forEach {
                repository.getCurrencyDetails(it.id, it.icon)
            }
        } catch (error: Exception) {
            AppLogger.error(
                this::class.java.name,
                error.message.toString()
            )
        }
    }
}