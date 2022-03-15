package com.cryptotrend.repository.mapper

import com.cryptotrend.data.datasource.local.database.entity.CryptoCurrencyEntity
import org.junit.Assert
import org.junit.Test

class MapperKtTest {

    @Test
    fun `Convert crypto currency database entity to UI model successfully`() {
        //Given
        val cryptoCurrencyEntity =
            CryptoCurrencyEntity("bitcoin1", "Bitcoin", "btc", 1212, 12.23f, 0.12f)

        //when
        val uiModel = cryptoCurrencyEntity.toUiModel()

        //then
        Assert.assertEquals("bitcoin1", uiModel.id)
        Assert.assertEquals("Bitcoin", uiModel.name)
        Assert.assertEquals("btc", uiModel.symbol)
        Assert.assertEquals(1212, uiModel.icon)
        Assert.assertEquals(12.23f, uiModel.usd)
        Assert.assertEquals(0.12f, uiModel.btc)
    }

}