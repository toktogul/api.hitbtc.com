package com.cryptocurrency.data

import com.cryptocurrency.data.model.Candle
import com.cryptocurrency.data.model.Currency
import com.cryptocurrency.data.model.Symbol
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoService {

    @GET("symbol")
    fun fetchSymbols(): Call<List<Symbol>>

    @GET("currency/{currencyId}")
    fun getCurrency(@Path("currencyId") path: String): Call<Currency>

    @GET("candles/{symbolId}?period=D1&limit=60")
    fun getCandles(@Path("symbolId") symbol: String): Call<List<Candle>>
}