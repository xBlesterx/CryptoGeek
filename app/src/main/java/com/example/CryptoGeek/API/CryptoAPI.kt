package com.example.CryptoGeek.API

object CryptoAPI{
    //https://cryptoicons.org/api/icon/ to be used if the current obe is not working
    const val imageUrl = "https://coinicons-api.vercel.app/api/icon/"
    //Max load of crypto on the app
    const val MAX_COIN_LOAD = 100
    //my api from coinmarketcap
    const val API_KEY = "c08a6c6f-9e5d-410d-8a1b-24f7ca9ca74c"
    //API link
    const val API_URI_INITIAL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest"

    const val BASE_URL = "https://pro.coinmarketcap.com/"


    fun get_header_map(): Map<String, String>{
        val headerMap = mutableMapOf<String, String>()
        headerMap["Accept"] = "application/json"
        headerMap["Content-Type"] = "application/json"
        headerMap["X-CMC_PRO_API_KEY"] = API_KEY
        return  headerMap
    }
}
