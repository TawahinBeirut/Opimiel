package com.example.opimiel_frontend

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity()  {

    lateinit var id: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        var intent: Intent = intent;
        id= intent.getStringExtra("UserId").toString();

    }
}