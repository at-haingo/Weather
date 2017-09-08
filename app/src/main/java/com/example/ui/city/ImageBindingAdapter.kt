package com.example.ui.city

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Hai on 8/24/2017.
 */
object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    fun loadImage(imageView: ImageView, url: String) {
        Picasso.with(imageView.context).load(url).into(imageView)
    }

    private fun getDateTime(time: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val target = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = sdf.parse(time)
        val dateTime = target.format(date)
        return dateTime
    }

    @JvmStatic
    @BindingAdapter("bind:dateTime")
    fun setDateTime(textView: TextView, text: String) {
        textView.text = getDateTime(text)
    }
}
