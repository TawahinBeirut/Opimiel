package com.example.opimiel_frontend

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ConnexionPage : AppCompatActivity()  {
    private lateinit var buttonStart: Button;
    override fun onCreate(savedInstanceState: Bundle?) {

        // Fais la connexion
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion_page);
        buttonStart = findViewById<Button>(R.id.button);
        buttonStart.setOnClickListener{
            var intent: Intent = Intent(this,MainActivity::class.java);

            // Vrai Id d'utilisateur
            intent.putExtra("UserId","b898a640-2b5f-4565-b58d-06899d774c66");
            startActivity(intent);
        }
    }
}