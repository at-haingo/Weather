package com.example.ui.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.example.quanghai.weather.R
import kotlinx.android.synthetic.main.fragment_new_city.*
import kotlinx.android.synthetic.main.fragment_new_city.view.*

/**
 *
 * Created by Hai on 8/30/2017.
 */
class NewCityFragment : Fragment() {
    private val city = arrayOf("DaNang", "HCM", "HaNoi", "DakNong")
    private var mAddNewCityListener: AddNewCityListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_new_city, container, false)
        view.tvSearch.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val cityName = tvSearch.text.toString().trim()
//                activity.supportFragmentManager.popBackStack()
//                MainActivity().replaceFragment(activity, CityViewPagerFragment.getInstance(cityName))
                mAddNewCityListener?.onAddNewCity(cityName)
                closeKeyboard()
                handled = true
            }
            handled
        }
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, city)
        view.tvSearch.setAdapter(adapter)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mAddNewCityListener = context as AddNewCityListener
    }

    private fun closeKeyboard() {
        val imm: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    interface AddNewCityListener {
        fun onAddNewCity(city: String)
    }
}
