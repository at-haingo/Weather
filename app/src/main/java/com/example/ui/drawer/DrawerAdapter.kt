package com.example.ui.drawer

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quanghai.weather.R
import com.example.quanghai.weather.databinding.ItemCityBinding
import kotlinx.android.synthetic.main.item_city.view.*

/**
 * Created by Hai on 9/6/2017.
 */
class DrawerAdapter(private val mOnCityClickListener: OnCityClickListener, private val mCityList: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private val TYPE_MY_LOCATION = 1000
        private val TYPE_CITY = 1001
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val inflate: LayoutInflater = LayoutInflater.from(parent?.context)
        when (viewType) {
            TYPE_MY_LOCATION -> {
                val view: View = inflate.inflate(R.layout.item_header_drawer, parent, false)
                return MyLocationHolder(view, mOnCityClickListener)
            }
            TYPE_CITY -> {
                val binding: ItemCityBinding = DataBindingUtil.inflate(inflate, R.layout.item_city, parent, false)
                return CityHolder(binding, mOnCityClickListener)
            }
        }
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? MyLocationHolder)?.onClick()
        (holder as? CityHolder)?.bindCity(mCityList[position - 1])
    }

    override fun getItemCount() = mCityList.size + 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_MY_LOCATION
        }
        return TYPE_CITY
    }

    class MyLocationHolder(itemView: View, private val mOnCityClickListener: OnCityClickListener) : RecyclerView.ViewHolder(itemView) {
        fun onClick() {
            itemView.setOnClickListener {
                mOnCityClickListener.onMyLocationClick()
            }
        }
    }

    class CityHolder(private val binding: ItemCityBinding, private val mOnCityClickListener: OnCityClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bindCity(city: String) {
            binding.city = city
            binding.root.tvCity.setOnClickListener {
                mOnCityClickListener.onCityClick(adapterPosition)
            }
        }
    }

    interface OnCityClickListener {
        fun onCityClick(position: Int)
        fun onMyLocationClick()
    }
}
