package com.example.ui.city

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.model.ItemForeCast
import com.example.quanghai.weather.R
import com.example.quanghai.weather.databinding.ItemForecastHourBinding

/**
 * Created by Hai on 8/25/2017.
 */
class ForeCastHourAdapter(private val foreCasts: List<ItemForeCast>) : RecyclerView.Adapter<ForeCastHourAdapter.ForeCastHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForeCastHolder {
        val inflate: LayoutInflater = LayoutInflater.from(parent?.context)
        val binding: ItemForecastHourBinding = DataBindingUtil.inflate(inflate, R.layout.item_forecast_hour, parent, false)
        return ForeCastHolder(binding)
    }

    override fun onBindViewHolder(holder: ForeCastHolder?, position: Int) {
        holder?.bindData(foreCasts[position])
    }

    override fun getItemCount() = foreCasts.size

    class ForeCastHolder(private val binding: ItemForecastHourBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(foreCast: ItemForeCast) {
            binding.foreCast = foreCast
        }
    }
}
