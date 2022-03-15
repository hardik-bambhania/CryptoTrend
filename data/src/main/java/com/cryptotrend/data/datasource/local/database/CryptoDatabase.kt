package com.cryptotrend.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cryptotrend.data.datasource.local.database.dao.CryptoCurrencyDao
import com.cryptotrend.data.datasource.local.database.entity.CryptoCurrencyEntity


/**
 * Crypto Currency Database class
 */
@Database(
    version = 1,
    entities = [CryptoCurrencyEntity::class]
)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun getCryptoCurrencyDao(): CryptoCurrencyDao
}