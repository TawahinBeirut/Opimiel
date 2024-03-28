package com.example.opimiel_frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ConnexionPage : AppCompatActivity()  {
    private lateinit var buttonLogin: Button;
    private lateinit var buttonRegister: Button;
    private lateinit var usernameInput: TextInputEditText;
    private lateinit var passwordInput: TextInputEditText;

    companion object{
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Fais la connexion
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.connexion_page);
        buttonRegister = findViewById(R.id.register_button);
        buttonLogin = findViewById(R.id.login_button);
        usernameInput = findViewById(R.id.login_username);
        passwordInput = findViewById(R.id.login_password);

        buttonLogin.setOnClickListener({
                val email = usernameInput.text.toString();
                val password = passwordInput.text.toString();
                if(email.isNotEmpty() && password.isNotEmpty()){
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener({
                        if(it.isSuccessful){
                            Toast.makeText(this, "Login in successfully !", Toast.LENGTH_SHORT).show()

                            var intent: Intent = Intent(this,MainActivity::class.java);

                            // Vrai Id d'utilisateur
                            intent.putExtra("email", email);
                            startActivity(intent);
                        }
                    }).addOnFailureListener({
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()

                    })
                }
        })

        buttonRegister.setOnClickListener({
            val email = usernameInput.text.toString();
            val password = passwordInput.text.toString();
            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener({
                    if(it.isSuccessful){
                        Toast.makeText(this, "Account created Successfully !", Toast.LENGTH_SHORT).show()
                    }
                }).addOnFailureListener({
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()

                })
            }
        })
        /*buttonLogin.setOnClickListener{
            var intent: Intent = Intent(this,MainActivity::class.java);

            // Vrai Id d'utilisateur
            intent.putExtra("UserId","b898a640-2b5f-4565-b58d-06899d774c66");
            startActivity(intent);
        }
        */
    }
}