package com.example.CryptoGeek.API

import kotlin.math.pow

object MathOperation {
    //round the values of the 1h, 24h, 7d, and the price of the crypto
    fun round(value: Double): Double{
        var newValue = value
        val factor = 10.0.pow(2)
        newValue *= factor
        val tmp = Math.round(newValue)
        return tmp / factor
    }
}
