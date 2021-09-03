package com.example.harajtask.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harajtask.model.Data
import com.google.gson.annotations.SerializedName
import org.json.JSONArray

class DataViewModel: ViewModel() {
    @SerializedName("data")
    var dataList = MutableLiveData<ArrayList<Data>>()
    var newlist = arrayListOf<Data>()
    var title: String = ""
    var username: String = ""
    var thumbURL: String = ""
    var commentCount: Int = 0
    var city: String = ""
    var date: Int = 0
    var body: String = ""
    private fun loadJson(context: Context): ArrayList<Data> {
        val fileName = "data.json"
        val bufferReader = context.assets.open(fileName).bufferedReader()
        val data = bufferReader.use{
            it.readText()
        }
        val jsonArray  = JSONArray(data)
        for(i in 0 until jsonArray.length()) {
            title = jsonArray.getJSONObject(i).getString("title")
            username = jsonArray.getJSONObject(i).getString("username")
            thumbURL = jsonArray.getJSONObject(i).getString("thumbURL")
            commentCount = jsonArray.getJSONObject(i).getInt("commentCount")
            city = jsonArray.getJSONObject(i).getString("city")
            date = jsonArray.getJSONObject(i).getInt("date")
            body = jsonArray.getJSONObject(i).getString("body")
            val jsonSerialized = Data(title, username, thumbURL, commentCount, city, date, body)
            newlist.add(jsonSerialized)
        }
        dataList.value = newlist

        return newlist
    }
}