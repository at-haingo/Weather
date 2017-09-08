package com.example.model

/**
 * Created by Hai on 8/31/2017.
 */
data class City(val id: Int, val name: String, val country: String, val coord: Coordinates){
    override fun toString(): String {
        return name
    }
}