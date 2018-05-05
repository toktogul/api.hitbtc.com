package com.cryptocurrency.data

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.cryptocurrency.data.model.Symbol

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<Symbol>)

    @Query("SELECT * FROM currencies")
    fun getAll(): DataSource.Factory<Int, Symbol>

    @Query("SELECT * FROM currencies WHERE baseCurrency=:currencyId")
    fun getSymbol(currencyId: String): LiveData<Symbol>
}