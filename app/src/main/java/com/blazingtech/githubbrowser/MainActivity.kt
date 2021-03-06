package com.blazingtech.githubbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blazingtech.githubbrowser.home.HomeFragment

class MainActivity : AppCompatActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.screenContainer, HomeFragment())
                .commit()
        }
    }



}