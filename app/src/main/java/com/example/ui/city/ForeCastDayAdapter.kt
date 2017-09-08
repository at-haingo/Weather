package com.example.ui.city

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.model.ItemForeCast
import com.example.quanghai.weather.R
import com.example.quanghai.weather.databinding.ItemForecastDayBinding

/**
 * Created by Hai on 8/28/2017.
 */
class ForeCastDayAdapter(private val foreCasts: List<ItemForeCast>) : RecyclerView.Adapter<ForeCastDayAdapter.ForeCastHolder>() {
    override fun getItemCount() = foreCasts.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForeCastHolder {
        val inflate: LayoutInflater = LayoutInflater.from(parent?.context)
        val binding: ItemForecastDayBinding = DataBindingUtil.inflate(inflate, R.layout.item_forecast_day, parent, false)
        return ForeCastHolder(binding)
    }

    override fun onBindViewHolder(holder: ForeCastHolder?, position: Int) {
        holder?.bind(foreCasts[position])
    }

    class ForeCastHolder(private val binding: ItemForecastDayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(foreCast: ItemForeCast) {
            binding.foreCast = foreCast
        }
    }
}
