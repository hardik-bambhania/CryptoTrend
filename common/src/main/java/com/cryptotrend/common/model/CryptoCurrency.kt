package com.cryptotrend.common.model

/**
 * CryptoCurrency UI Model
 */
data class CryptoCurrency(
    val id: String,
    val name: String,
    val symbol: String,
    val icon: Int,
    val usd: Float,
    val btc: Float
)
