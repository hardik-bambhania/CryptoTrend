package com.cryptotrend.data.datasource.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CryptoDatabaseTest {

    private lateinit var appDatabase: CryptoDatabase

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CryptoDatabase::class.java
        ).build()
    }


    @Test
    fun databaseCreateSuccessfully() {
        Assert.assertNotNull(appDatabase)
    }

    @Test
    fun accessCryptoCurrencyDaoSuccessfully() {
        Assert.assertNotNull(appDatabase.getCryptoCurrencyDao())
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

}