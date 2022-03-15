package com.cryptotrend.data.datasource.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database table to store crypto currency details
 */
@Entity
data class CryptoCurrencyEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
    val icon: Int,
    val usd: Float,
    val btc: Float
)
