package com.cryptotrend.data.datasource.remote.model

data class CryptoDetails(
    val id: String,
    val name: String,
    val symbol: String,
    val market_data: MarketData
)
