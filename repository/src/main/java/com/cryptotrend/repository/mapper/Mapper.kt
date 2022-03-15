package com.cryptotrend.repository.mapper

import com.cryptotrend.common.model.CryptoCurrency
import com.cryptotrend.data.datasource.local.database.entity.CryptoCurrencyEntity

/**
 * Convert database CryptoCurrency Entity to UI Model
 */
fun CryptoCurrencyEntity.toUiModel(): CryptoCurrency {
    return CryptoCurrency(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        icon = this.icon,
        usd = this.usd,
        btc = this.btc
    )
}