package com.cryptocurrency.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cryptocurrency.data.model.Symbol


@Database(entities = [Symbol::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun currencyDao(): CurrenciesDao
}