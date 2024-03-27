package com.example.opimiel_frontend

import ApiService
import PostSubjectRequest
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

            try {

                val requestBody = PostSubjectRequest(userId, input.text.toString())
                Log.d("test",requestBody.toString())
                val call = apiService.postSubject(requestBody);

                call.enqueue(object : Callback<MessageResponse> {
                    override fun onResponse(
                        call: Call<MessageResponse>,
                        response: Response<MessageResponse>
                    ) {
                        if (response.isSuccessful){

                            Log.d("reussite requete",response.toString())

                        }else{
                            Log.d("erreur requete",response.toString())
                        }
                    }

                    override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })


            }catch (e: Exception) {
                Log.d("Network exception",e.toString())
            }
        }


    }
}