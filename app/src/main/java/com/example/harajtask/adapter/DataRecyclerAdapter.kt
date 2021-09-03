package com.example.harajtask.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.harajtask.R
import com.example.harajtask.model.Data
import com.example.harajtask.view.ItemActivity
import com.example.harajtask.viewmodel.DataViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card.view.*

class DataRecyclerAdapter(val viewModel:DataViewModel, val arrayList: ArrayList<Data>, context: Context): RecyclerView.Adapter<DataRecyclerAdapter.DataViewHolder>() {

    val context = context

    inner class DataViewHolder(val binding: View) : RecyclerView.ViewHolder(binding){

        fun bind(data: Data) {
            itemView.item_title.text = data.title
            Picasso.get().load(data.thumbURL).fit().into(itemView.thumbURL)
            itemView.username.text = data.username
            itemView.city.text = data.city
            if(data.commentCount != 0){
                itemView.commentCount.text = "("+ data.commentCount.toString() + ")"
                itemView.ic_comment.isVisible = true
            }else{
                itemView.commentCount.isVisible = false
                itemView.ic_comment.isVisible = false
            }
            itemView.item_card_layout.setOnClickListener{
                val intent = Intent(context, ItemActivity::class.java).apply {
                    putExtra("title", data.title)
                    putExtra("username", data.username)
                    putExtra("thumbURL", data.thumbURL)
                    putExtra("city", data.city)
                    putExtra("date", data.date)
                    putExtra("body", data.body)
                }
                context.startActivity(intent)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)

        return DataViewHolder(root)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(arrayList.get(position))

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}