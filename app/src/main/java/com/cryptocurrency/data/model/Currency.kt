package com.cryptocurrency.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "currencies")
data class Currency(
        @PrimaryKey var id: String = "",
        var fullName: String = "",
        var payoutFee: String = "",
        var crypto: Boolean = false,
        var delisted: Boolean = false,
        var payinEnabled: Boolean = false,
        var payoutEnabled: Boolean = false,
        var payinPaymentId: Boolean = false,
        var transferEnabled: Boolean = false,
        var payoutIsPaymentId: Boolean = false,
        var payinConfirmations: Int = 0
)