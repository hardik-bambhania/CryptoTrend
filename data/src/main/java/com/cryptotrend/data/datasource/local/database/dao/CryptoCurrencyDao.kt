package com.cryptotrend.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cryptotrend.data.datasource.local.database.entity.CryptoCurrencyEntity
import kotlinx.coroutines.flow.Flow

/**
 * This interface allow to access crypto currency data from database
 */
@Dao
interface CryptoCurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cryptoCurrencyEntity: CryptoCurrencyEntity)

    @Query("SELECT * FROM cryptocurrencyentity")
    fun getAllCryptoCurrency(): Flow<List<CryptoCurrencyEntity>>
}