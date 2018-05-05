package com.cryptocurrency.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.cryptocurrency.data.AppDb
import com.cryptocurrency.data.CryptoService
import com.cryptocurrency.data.model.Symbol
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val service: CryptoService,
                    private val room: AppDb) : ViewModel() {

    fun fetchSymbols(): LiveData<PagedList<Symbol>> {
        return LivePagedListBuilder<Int, Symbol>(room.currencyDao().getAll(), 30)
                .setBoundaryCallback(object : PagedList.BoundaryCallback<Symbol>() {
                    override fun onZeroItemsLoaded() = loadData()
                })
                .build()
    }

    private fun loadData() {
        service.fetchSymbols().enqueue(object : Callback<List<Symbol>> {
            override fun onFailure(call: Call<List<Symbol>>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<List<Symbol>>?, response: Response<List<Symbol>>?) {
                if (response!!.isSuccessful) {
                    Thread({
                        room.currencyDao().insertList(response.body()!!)
                    }).start()
                }
            }
        })
    }
}