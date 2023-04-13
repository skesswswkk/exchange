package com.example.exchange.model

import com.google.gson.annotations.SerializedName

data class Exchange (
    @SerializedName("USDKRW")
    val krw: Double,
    @SerializedName("USDJPY")
    val jpy: Double,
    @SerializedName("USDPHP")
    val php: Double
)