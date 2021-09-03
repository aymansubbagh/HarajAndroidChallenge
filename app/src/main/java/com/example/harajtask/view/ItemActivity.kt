package com.example.harajtask.view

import android.content.Intent
import android.icu.util.UniversalTimeScale.toLong
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.harajtask.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item.*
import java.text.SimpleDateFormat
import java.util.*

class ItemActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title = intent.getStringExtra("title")
        val username = intent.getStringExtra("username")
        val thumbURL = intent.getStringExtra("thumbURL")
        val city = intent.getStringExtra("city")

        d_item_title.text = title
        d_username.text = username
        Picasso.get().load(thumbURL).into(d_thumbURL)
        d_city.text = city

        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm a")
        val netDate = Date(toLong(intent.getIntExtra("date",0).toLong(),4)*1000)
        Log.i("date", sdf.format(netDate))

        d_date.text = sdf.format(netDate)
        d_body.text = intent.getStringExtra("body")
        ic_share.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, title)
                type = "text/plain"
            }
            val share = Intent.createChooser(shareIntent, "Share to...")
            startActivity(share)

        }
    }
}