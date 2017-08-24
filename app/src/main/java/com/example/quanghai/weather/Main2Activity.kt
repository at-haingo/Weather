package com.example.quanghai.weather

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.quanghai.weather.model.Wind
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

fun topFuntion(){}
class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        Log.d("xxxStatic", "${++Test.a}")
//        Log.d("xxxStatic", "${++Test.a}")
//        Log.d("xxxStatic", "${++A.c}")
//        Log.d("xxxStatic", "${++A.c}")
//        Log.d("xxx", "${++VVV().v}")
//        Log.d("xxx", "${++VVV().v}")

        // trick change value of final variable
        var counter = 0
        val inc = { counter++ }
        val aa = { x: Int, y: Int -> x + y }
        Log.d("xxx", "" + aa(1, 2))
//
//    class Ref<T>(var value: T)
//    val counter = Ref(0)
//    val inc = { counter.value++
        val speed = Wind::speed
        val ccc: List<String> = listOf("ss")
        val topFuntion = ::topFuntion
        btnTest.setOnClickListener {
            // counter++
            Log.d("xxx", "" + inc())
        }

        val vp = ViewPager(this)
        vp.addOnPageChangeListener(object : Page {
            override fun onPageScrollStateChanged(state: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        val list = listOf("aa", "Bb", "cc")
        list.maxBy { a -> a.length }
        list.maxBy { it.length }
    }

    object Test {
        lateinit var dd: String
        val dddd: String by lazy { "xxxx" }
        var a = 10
        val b = 1
    }

    companion object A {
        lateinit var dd: String
        val dddd: String by lazy { "xxxx" }
        var c = 10
        fun AAA() {

        }
    }

    class VVV {
        var v:Int = 10
        fun abc(): Int = v
    }


    interface Page : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
        }
    }
}
