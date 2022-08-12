package com.example.CryptoGeek.adapter

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.CryptoGeek.API.CryptoAPI
import com.example.CryptoGeek.R
import com.example.CryptoGeek.API.MathOperation
import com.example.CryptoGeek.ui.MainActivity
import com.example.CryptoGeek.model.listing.Coin
import kotlinx.android.synthetic.main.coin_layout.view.*
import java.lang.StringBuilder


class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var coinIcon = itemView.coinIcon
    var coinSymbol = itemView.coinSymbol
    var coinName = itemView.coinName
    var coinPrice = itemView.priceUsd
    var oneHourChange = itemView.oneHour
    var twentyFourChange = itemView.twentyFourHour
    var sevenDayChange = itemView.sevenDay
}

class CoinAdapter(recyclerView: RecyclerView, private var activity: Activity, var items: List<Coin>) : RecyclerView.Adapter<CoinViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.coin_layout, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {

        val coinModel = items[position]

        val item = holder as CoinViewHolder

        item.coinName.text = coinModel.name
        item.coinSymbol.text = coinModel.symbol
        item.coinPrice.text = MathOperation.round(coinModel.quote.usd.price).toString()
        item.oneHourChange.text = MathOperation.round(coinModel.quote.usd.percentChange1h).toString() + "%"
        item.twentyFourChange.text = MathOperation.round(coinModel.quote.usd.percentChange24h).toString() + "%"
        item.sevenDayChange.text = MathOperation.round(coinModel.quote.usd.percentChange7d).toString() + "%"

        Picasso.with(activity.baseContext)
            .load(StringBuilder(CryptoAPI.imageUrl)
                .append(coinModel.symbol!!.lowercase())

                .toString())
            .into(item.coinIcon)

        //Set color
        item.oneHourChange.setTextColor(if (coinModel.quote.usd.percentChange1h.toString().contains("-"))
            Color.parseColor("#FF0000")
        else
            Color.parseColor("#32CD32")
        )

        item.twentyFourChange.setTextColor(if (coinModel.quote.usd.percentChange24h.toString().contains("-"))
            Color.parseColor("#FF0000")
        else
            Color.parseColor("#32CD32")
        )

        item.sevenDayChange.setTextColor(if (coinModel.quote.usd.percentChange7d.toString().contains("-"))
            Color.parseColor("#FF0000")
        else
            Color.parseColor("#32CD32")
        )
    }


    internal var loadMore: ILoadMore? = null
    var isLoading: Boolean = false
    var visibleThreshold = 5
    var lastVisibleItem: Int = 0
    var totalItemCount: Int = 0

    init {
        val linearLayout = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayout.itemCount
                lastVisibleItem = linearLayout.findLastVisibleItemPosition()

            }
        })
    }

    fun setLoadMore(loadMore: MainActivity) {
        this.loadMore = loadMore
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun  setLoaded(){
        isLoading = true
    }

    fun updateData(coinModels: List<Coin>)
    {
        this.items = coinModels
        notifyDataSetChanged()
    }

}