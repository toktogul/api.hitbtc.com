package com.cryptocurrency

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import android.arch.persistence.room.Room
import com.cryptocurrency.data.AppDb
import com.cryptocurrency.data.CryptoService
import com.cryptocurrency.ui.candles.CandlesViewModel
import com.cryptocurrency.ui.detail.DetailViewModel
import com.cryptocurrency.ui.list.CURRENCY_ID
import com.cryptocurrency.ui.list.MainViewModel
import com.cryptocurrency.ui.list.SYMBOL_ID
import org.koin.android.architecture.ext.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CryptoApp : Application() {
    private lateinit var db: AppDb
    private lateinit var networkService: CryptoService


    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDb::class.java, "currencies").build()
        networkService = networkService()
        startKoin(this, getModules())
    }

    private fun networkService(): CryptoService {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.hitbtc.com/api/2/public/")
                .build()
        return retrofit.create(CryptoService::class.java)
    }

    private fun getModules(): List<Module> {
        val modules = org.koin.dsl.module.applicationContext {
            viewModel { MainViewModel(networkService, db) }
            viewModel { params -> DetailViewModel(params[CURRENCY_ID], networkService) }
            viewModel { params -> CandlesViewModel(params[SYMBOL_ID], networkService) }
        }
        return listOf(modules)
    }
}