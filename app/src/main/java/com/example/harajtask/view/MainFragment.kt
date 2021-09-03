package com.example.harajtask.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harajtask.R
import com.example.harajtask.adapter.DataRecyclerAdapter
import com.example.harajtask.model.Data
import com.example.harajtask.viewmodel.DataViewModel
import com.example.harajtask.viewmodel.DataViewModelFactory
import org.json.JSONArray

class MainFragment : Fragment() {

    private var layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)
    private lateinit var viewModel: DataViewModel
    private lateinit var recycler: RecyclerView
    var arrList = ArrayList<Data>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recyclerview)
        val factory = DataViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(DataViewModel::class.java)
        loadJson(this.requireContext())
        getAdapter(arrList, this.requireContext())
    }

    private fun getAdapter(arrList: ArrayList<Data>, context: Context){
        recycler.layoutManager = layoutManager
        recycler.adapter = DataRecyclerAdapter(viewModel,arrList, context)
    }
    private fun loadJson(context: Context) {
        val fileName = "data.json"
        val bufferReader = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        val jsonArray  = JSONArray(bufferReader)
        for(i in 0 until jsonArray.length()) {
            val jsonSerialized = Data(jsonArray.getJSONObject(i).getString("title"),
                jsonArray.getJSONObject(i).getString("username"),
                jsonArray.getJSONObject(i).getString("thumbURL"),
                jsonArray.getJSONObject(i).getInt("commentCount"),
                jsonArray.getJSONObject(i).getString("city"),
                jsonArray.getJSONObject(i).getInt("date"),
                jsonArray.getJSONObject(i).getString("body"))
            arrList.add(jsonSerialized)
        }
    }


}