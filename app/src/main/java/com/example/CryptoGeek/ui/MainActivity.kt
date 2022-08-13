package com.example.CryptoGeek.ui


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.CryptoGeek.API.CryptoAPI
import com.example.CryptoGeek.R
import com.example.CryptoGeek.adapter.CoinAdapter
import com.example.CryptoGeek.adapter.ILoadMore
import com.example.CryptoGeek.model.listing.Coin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_coins.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.jvm.JvmField as JvmField1

class MainActivity() : AppCompatActivity(), ILoadMore,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    //Declare variable
    internal var items: MutableList<Coin> = ArrayList()
    lateinit var adapter: CoinAdapter
    private lateinit var client: OkHttpClient
    private lateinit var request: Request


    override fun onLoadMore() {
        Toast.makeText(
            this@MainActivity,
            "Data max is " + CryptoAPI.MAX_COIN_LOAD,
            Toast.LENGTH_SHORT
        ).show()
    }


    private fun loadFirst10Coin() {
        client = OkHttpClient()
        request = Request.Builder()
            .url(String.format(CryptoAPI.API_URI_INITIAL))
            .header("Accept", "application/json")
            .addHeader("X-CMC_PRO_API_KEY", CryptoAPI.API_KEY)
            .build()


        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("ERROR", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.let {
                        val body = it.string()
                        Log.d("Debug Coin", body)
                        val jsonResponse = JSONObject(body)

                        if (jsonResponse.has("status")) {
                            val json = jsonResponse.getJSONObject("status")
                            val errorCode = json.get("error_code")
                            if (errorCode != 0) {
                                val errorMessage = json.get("error_message")
                                runOnUiThread {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Could not receive data: $errorMessage",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                val coinData = jsonResponse.getJSONArray("data").toString()
                                val gson = Gson()

                                items =
                                    gson.fromJson(
                                        coinData,
                                        object : TypeToken<List<Coin>>() {}.type
                                    )
                                runOnUiThread {
                                    adapter.updateData(items)
                                }
                            }
                        }
                    }
                }

            })


    }

    private fun post() {
        coin_refreash.post { loadFirst10Coin() }
        coin_recycler_view.layoutManager = LinearLayoutManager(this)
        setUpAdapter(items)
        refreash()
    }

    private fun refreash() {
        coin_refreash.setOnRefreshListener {
            items.clear() // Remove all item
            loadFirst10Coin()
            setUpAdapter(items)
            coin_refreash.isRefreshing = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_coins)
        post()
        lv_listView.setOnQueryTextListener(this)
    }

    private fun setUpAdapter(items: MutableList<Coin> = ArrayList()) {
        adapter = CoinAdapter(coin_recycler_view as RecyclerView, this@MainActivity, items)
        (coin_recycler_view as RecyclerView).adapter = adapter
        adapter.setLoadMore(this)
    }


    private fun searchItems(query: String) {
        val searcher: MutableList<Coin> = ArrayList()
        for (item in items) {
            if (item.name.lowercase() == query.lowercase() || item.symbol.lowercase() == query.lowercase()) {
                searcher.add(item)
            }
            adapter.updateData(searcher)
            coin_recycler_view.layoutManager = LinearLayoutManager(this)
            setUpAdapter(searcher)
        }
        if (searcher.isEmpty()) Toast.makeText(this, "Can't find this Crypto!! Try again", Toast.LENGTH_SHORT)
            .show()


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) searchItems(query)

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }
}
