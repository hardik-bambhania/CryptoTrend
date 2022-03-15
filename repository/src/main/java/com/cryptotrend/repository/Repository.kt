package com.cryptotrend.repository

import com.cryptotrend.common.logger.AppLogger
import com.cryptotrend.data.datasource.local.database.CryptoDatabase
import com.cryptotrend.data.datasource.local.database.entity.CryptoCurrencyEntity
import com.cryptotrend.data.datasource.remote.CryptoRemoteDataSource
import com.cryptotrend.repository.mapper.toUiModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository is responsible to pass data to UI layer
 */
class Repository @Inject constructor(
    private val remoteDataSource: CryptoRemoteDataSource,
    private val localDataSource: CryptoDatabase
) {

    private external fun prepareLog(symbol: String, price: String): String

    /**
     * Get crypto currency details.
     * @param id-Crypto currency ID
     * @param icon - Crypto currency Icon
     */
    suspend fun getCurrencyDetails(id: String, icon: Int) {
        val response = remoteDataSource.getCurrencyDetails(id)

        insert(
            CryptoCurrencyEntity(
                id = response.id,
                name = response.name,
                symbol = response.symbol,
                icon = icon,
                usd = response.market_data.current_price.usd,
                btc = response.market_data.current_price.btc
            )
        )

        val logMessage =
            prepareLog(response.symbol, response.market_data.current_price.usd.toString())
        AppLogger.info(this::class.java.name, logMessage)
    }

    /**
     * Get All crypto currency from database
     */
    fun getAllCryptoCurrency() = localDataSource.getCryptoCurrencyDao()
        .getAllCryptoCurrency()
        .map { currencyList ->
            currencyList.map { cryptoCurrencyEntity ->
                cryptoCurrencyEntity.toUiModel()
            }
        }

    /**
     * Insert/Update crypto currency data in database
     */
    private suspend fun insert(cryptoCurrencyEntity: CryptoCurrencyEntity) =
        localDataSource.getCryptoCurrencyDao().insert(cryptoCurrencyEntity)
}