package com.example.harajtask.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataViewModelFactory: ViewModelProvider.Factory {
    override fun <view : ViewModel?> create(modelClass: Class<view>): view {
        if(modelClass.isAssignableFrom(DataViewModel::class.java)){
            Log.i("seventh log","------")
            return DataViewModel() as view
        }
        throw IllegalArgumentException("UnknowViewModel")
    }
}