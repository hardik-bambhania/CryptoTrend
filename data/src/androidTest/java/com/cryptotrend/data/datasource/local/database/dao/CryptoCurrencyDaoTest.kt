package com.cryptotrend.data.datasource.local.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cryptotrend.data.datasource.local.database.CryptoDatabase
import com.cryptotrend.data.datasource.local.database.entity.CryptoCurrencyEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CryptoCurrencyDaoTest {

    private lateinit var database: CryptoDatabase
    private lateinit var cryptoCurrencyDao: CryptoCurrencyDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CryptoDatabase::class.java
        ).build()

        cryptoCurrencyDao = database.getCryptoCurrencyDao()
    }

    @Test
    fun insertCryptoCurrency() = runBlocking {
        //Given
        val cryptoCurrency = CryptoCurrencyEntity("bitcoin1", "Bitcoin", "btc", 1212, 12.23f, 0.12f)

        //When
        cryptoCurrencyDao.insert(cryptoCurrency)

        //Then
        val databaseData = cryptoCurrencyDao.getAllCryptoCurrency().first()
        Assert.assertEquals(1, databaseData.size)

        val insertedData = databaseData.first()
        Assert.assertEquals("bitcoin1", insertedData.id)
        Assert.assertEquals("Bitcoin", insertedData.name)
        Assert.assertEquals("btc", insertedData.symbol)
        Assert.assertEquals(1212, insertedData.icon)
        Assert.assertEquals(12.23f, insertedData.usd)
        Assert.assertEquals(0.12f, insertedData.btc)
    }


    @Test
    fun getAllCryptoCurrency() = runBlocking {
        //Given
        val cryptoCurrency1 =
            CryptoCurrencyEntity("bitcoin1", "Bitcoin", "btc", 1212, 12.23f, 0.12f)
        val cryptoCurrency2 =
            CryptoCurrencyEntity("ethereum1", "Ethereum", "eth", 2323, 9.23f, 0.02f)

        //When
        cryptoCurrencyDao.insert(cryptoCurrency1)
        cryptoCurrencyDao.insert(cryptoCurrency2)

        //Then
        val databaseData = cryptoCurrencyDao.getAllCryptoCurrency().first()
        Assert.assertEquals(2, databaseData.size)
    }


}