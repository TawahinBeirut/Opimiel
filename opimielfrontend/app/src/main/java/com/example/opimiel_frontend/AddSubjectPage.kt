package com.example.opimiel_frontend

import ApiService
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.opimiel_frontend.model.apiCalls.MessageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddSubjectPage: AppCompatActivity() {
    private lateinit var userId:String;
    private lateinit var input: EditText;
    private lateinit var buttonConfirm: Button;

    val retrofit = Retrofit.Builder()
        .baseUrl("https://opimiel.vercel.app/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subject);

        input = findViewById(R.id.editTextSujet);
        buttonConfirm = findViewById(R.id.buttonValider);

        var intent: Intent = intent;
        userId= intent.getStringExtra("userId").toString();

        buttonConfirm.setOnClickListener{

            val res = apiService.postSubject(input.text.toString(),userId)
            if (res.isSuccessful){
                Log.d("Reussite requete",res.toString());
                // Mettre une snackbar reussie ici
                val newIntent:Intent = Intent(it.context,MainActivity::class.java).apply {
                    putExtra("userId",userId);

                }
                startActivity(newIntent);
            }
            else{
                // Mettre une snackbar ou toast ici echec
                val resp = res.body();
                Log.d("Echec requete",resp?.message.toString())
            }
        }


    }
}