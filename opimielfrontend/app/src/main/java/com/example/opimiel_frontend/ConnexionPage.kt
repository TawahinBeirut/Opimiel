package com.example.opimiel_frontend

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ConnexionPage : AppCompatActivity()  {
    private lateinit var buttonLogin: Button;
    private lateinit var usernameInput: TextInputEditText;
    private lateinit var passwordInput: TextInputEditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        // Fais la connexion
        super.onCreate(savedInstanceState)
        buttonLogin = findViewById(R.id.login_button);
       /* setContentView(R.layout.connexion_page);
        usernameInput = findViewById(R.id.login_username);
        passwordInput = findViewById(R.id.login_password);*/

        buttonLogin.setOnClickListener{
            var intent: Intent = Intent(this,MainActivity::class.java);

            // Vrai Id d'utilisateur
            intent.putExtra("UserId","b898a640-2b5f-4565-b58d-06899d774c66");
            startActivity(intent);
        }

    }
}