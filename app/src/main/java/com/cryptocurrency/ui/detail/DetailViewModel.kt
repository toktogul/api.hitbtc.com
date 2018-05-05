package com.cryptocurrency.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cryptocurrency.data.CryptoService
import com.cryptocurrency.data.model.Currency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val currencyId: String,
                      private val service: CryptoService) : ViewModel() {

    private val currencyLiveData = MutableLiveData<Currency>()

    init {
        getCurrencyInfo()
    }

    fun getCurrency(): LiveData<Currency> = currencyLiveData

    private fun getCurrencyInfo() {
        service.getCurrency(currencyId).enqueue(object : Callback<Currency> {
            override fun onFailure(call: Call<Currency>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<Currency>?, response: Response<Currency>?) {
                if (response!!.isSuccessful) {
                    currencyLiveData.value = response.body()!!
                }
            }
        })
    }
}