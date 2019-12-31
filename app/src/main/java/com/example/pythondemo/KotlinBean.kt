package com.example.pythondemo

import android.util.Log

class KotlinBean constructor(name: String){
    var name: String? = ""
    var data = ArrayList<String>()
    init {
        this.name = name
    }
    fun setData(str: String){
        this.data.add(str)
    }
    fun print(){
        for (it in data){
            Log.d("Kotlin Bean - $this.name",  it)
        }
    }
}