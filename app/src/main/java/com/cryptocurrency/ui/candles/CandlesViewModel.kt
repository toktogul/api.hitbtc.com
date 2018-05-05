package com.cryptocurrency.ui.candles

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cryptocurrency.data.model.Candle
import com.cryptocurrency.data.CryptoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CandlesViewModel(private val currencyId: String,
                       private val service: CryptoService) : ViewModel() {

    private val liveData = MutableLiveData<List<Candle>>()

    init {
        getCandles()
    }

    fun candles(): LiveData<List<Candle>> = liveData

    private fun getCandles() {
        service.getCandles(currencyId).enqueue(object : Callback<List<Candle>> {
            override fun onFailure(call: Call<List<Candle>>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<List<Candle>>?, response: Response<List<Candle>>?) {
                if (response!!.isSuccessful) {
                    response.body()!!.forEach {
                        it.detail = "${getDayAndMonth(it.timestamp!!)} - ${it.close}"
                    }
                    val list = response.body()!!.reversed()
                    liveData.value = list
                }
            }
        })
    }

    private fun getDayAndMonth(date: Date): String {
        return SimpleDateFormat("dd - MMMM").format(date).toString()
    }
}