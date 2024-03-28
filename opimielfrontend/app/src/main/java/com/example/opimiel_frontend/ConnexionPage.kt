package com.example.opimiel_frontend

import ApiService
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.opimiel_frontend.databinding.ActivityMainBinding
import com.example.opimiel_frontend.databinding.ConnexionPageBinding
import com.example.opimiel_frontend.model.apiCalls.AddUserResponse
import com.example.opimiel_frontend.model.apiCalls.MessageResponse
import com.example.opimiel_frontend.model.apiCalls.PostAddUserRequest
import com.example.opimiel_frontend.model.apiCalls.PostGetUser
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ConnexionPage : AppCompatActivity()  {
    private lateinit var buttonLogin: Button;
    private lateinit var buttonRegister: Button;
    private lateinit var usernameInput: TextInputEditText;
    private lateinit var passwordInput: TextInputEditText;
    private lateinit var binding: ConnexionPageBinding;


    val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://opimiel.vercel.app/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiService = retrofit.create(ApiService::class.java);

    companion object{
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Fais la connexion
        super.onCreate(savedInstanceState)
        binding = ConnexionPageBinding.inflate(layoutInflater)

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


                            try {

                                val requestBody = PostGetUser(email)
                                val call = apiService.getUser(requestBody);

                                call.enqueue(object : Callback<AddUserResponse> {
                                    override fun onResponse(
                                        call: Call<AddUserResponse>,
                                        response: Response<AddUserResponse>
                                    ) {
                                        if (response.isSuccessful){
                                            Snackbar.make(binding.root.rootView, "Requête réussie", Snackbar.LENGTH_LONG).show()

                                            val userid = response.body()?.userid;
                                            Log.d("reussite requete",response.toString())
                                            var intent: Intent = Intent(binding.root.context,MainActivity::class.java);
                                            // Vrai Id d'utilisateur
                                            intent.putExtra("UserId",userid);
                                            startActivity(intent);

                                        }else{
                                            Log.d("erreur requete",response.toString())
                                        }
                                    }

                                    override fun onFailure(call: Call<AddUserResponse>, t: Throwable) {
                                        TODO("Not yet implemented")
                                    }

                                })


                            }catch (e: Exception) {
                                Log.d("Network exception",e.toString())
                            }
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

                        try {

                            val requestBody = PostAddUserRequest("", email, password)
                            val call = apiService.addUser(requestBody);

                            call.enqueue(object : Callback<AddUserResponse> {
                                override fun onResponse(
                                    call: Call<AddUserResponse>,
                                    response: Response<AddUserResponse>
                                ) {
                                    if (response.isSuccessful){
                                        Snackbar.make(binding.root.rootView, "Requête réussie", Snackbar.LENGTH_LONG).show()

                                        val userid = response.body()?.userid;
                                        Log.d("reussite requete",response.toString())
                                        var intent: Intent = Intent(binding.root.context,MainActivity::class.java);
                                        // Vrai Id d'utilisateur
                                        intent.putExtra("UserId",userid);
                                        startActivity(intent);

                                    }else{
                                        Log.d("erreur requete",response.toString())
                                    }
                                }

                                override fun onFailure(call: Call<AddUserResponse>, t: Throwable) {
                                    TODO("Not yet implemented")
                                }

                            })


                        }catch (e: Exception) {
                            Log.d("Network exception",e.toString())
                        }


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