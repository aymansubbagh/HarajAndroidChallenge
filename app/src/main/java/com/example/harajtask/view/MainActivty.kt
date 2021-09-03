package com.example.harajtask.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.harajtask.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment, mainFragment)
            commit()
        }

    }
}