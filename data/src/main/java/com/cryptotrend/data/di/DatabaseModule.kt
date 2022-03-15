package com.cryptotrend.data.di

import android.content.Context
import androidx.room.Room
import com.cryptotrend.common.Constant
import com.cryptotrend.data.datasource.local.database.CryptoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Responsible to manage database dependency
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCryptoDatabase(@ApplicationContext context: Context): CryptoDatabase {
        return Room.databaseBuilder(
            context,
            CryptoDatabase::class.java,
            Constant.DATABASE_NAME
        ).build()
    }

}