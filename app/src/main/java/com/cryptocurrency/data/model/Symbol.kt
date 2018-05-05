package com.cryptocurrency.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable


@Entity(tableName = "currencies")
data class Symbol(
        @PrimaryKey var id: String = "",
        var baseCurrency: String = "",
        var quoteCurrency: String = "",
        var quantityIncrement: String = "",
        var tickSize: String = "",
        var takeLiquidityRate: String = "",
        var provideLiquidityRate: String = "",
        var feeCurrency: String = "") : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(baseCurrency)
        parcel.writeString(quoteCurrency)
        parcel.writeString(quantityIncrement)
        parcel.writeString(tickSize)
        parcel.writeString(takeLiquidityRate)
        parcel.writeString(provideLiquidityRate)
        parcel.writeString(feeCurrency)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Symbol> {
        override fun createFromParcel(parcel: Parcel): Symbol {
            return Symbol(parcel)
        }

        override fun newArray(size: Int): Array<Symbol?> {
            return arrayOfNulls(size)
        }
    }
}