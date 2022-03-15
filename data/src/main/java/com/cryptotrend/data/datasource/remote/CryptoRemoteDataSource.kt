package com.cryptotrend.data.datasource.remote

import com.cryptotrend.data.datasource.remote.model.CryptoDetails
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API for Crypto currency remote data
 */
interface CryptoRemoteDataSource {

    @GET("api/v3/coins/{id}")
    suspend fun getCurrencyDetails(
        @Path("id") id: String
    ): CryptoDetails
}