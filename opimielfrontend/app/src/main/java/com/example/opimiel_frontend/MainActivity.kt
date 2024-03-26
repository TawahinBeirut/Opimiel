package com.example.opimiel_frontend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var buttonStart: Button;
    override fun onCreate(savedInstanceState: Bundle?) {

        // Fais la connexion
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        buttonStart = findViewById<Button>(R.id.button);
        buttonStart.setOnClickListener{
            var intent: Intent = Intent(this,Home::class.java);

            // Vrai Id d'utilisateur
            intent.putExtra("UserId","2fa461f7-3344-4566-8371-e89d281885b2");
            startActivity(intent);
        }
    }

}