package com.example.opimiel_frontend

import ApiService
import OnSubjectClickListener
import PostFavoriteRequest
import PostReponseRequest
import PostSubjectRequest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.opimiel_frontend.databinding.ActivityMainBinding
import com.example.opimiel_frontend.model.apiCalls.MessageResponse
import com.example.opimiel_frontend.model.listeners.ChangePageListener
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),OnSubjectClickListener,ChangePageListener {

    private lateinit var id:String
    private lateinit var binding: ActivityMainBinding;
    private var LatUser: Float = (0).toFloat();
    private var LongUser : Float = (0).toFloat();
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        var intent: Intent = intent;
        id= intent.getStringExtra("UserId").toString();
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        fetchLocation()
        Log.d( "CACA",id);
        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    override fun onSubjectClick(subject: Subject) {
        val intent: Intent = Intent(this, SubjectPage::class.java).apply {
            // Ici on mets toutes les infos importantes
            putExtra("name", subject.name);
            putExtra("subjectId",subject.id);
            putExtra("authorId",subject.authorId)
            putExtra("userId",id)
        }

        startActivity(intent)
    }

    override fun getUserId(): String {
        return id;
    }

    override fun getFragmentName(): String {
        TODO("Not yet implemented")
    }

    override fun deleteFavorite(subject: Subject) {

        try {

            val requestBody = PostFavoriteRequest(id, subject.id)
            val call = apiService.deleteFavorite(requestBody);

            call.enqueue(object : Callback<MessageResponse> {
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful){
                        Snackbar.make(binding.root.rootView, "Requête réussie", Snackbar.LENGTH_LONG).show()
                        var intent: Intent = Intent(binding.root.context,MainActivity::class.java);
                        // Vrai Id d'utilisateur
                        intent.putExtra("UserId",id);
                        startActivity(intent);
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

    override fun postResponse(subject: Subject,value:Boolean) {
        try {

            val requestBody = PostReponseRequest(id,subject.id,value,this.LatUser,this.LongUser)
            val call = apiService.postResponse(requestBody);

            call.enqueue(object : Callback<MessageResponse> {
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful){

                        // faire un toast stp
                        Snackbar.make(binding.root.rootView, "Requête réussie", Snackbar.LENGTH_LONG).show()
                        var intent: Intent = Intent(binding.root.context,MainActivity::class.java);

                        Log.d("CACA OUI",response.body().toString())
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

    override fun getLatUser(): Float {
        return this.LatUser;
    }

    override fun getLongUser(): Float {
        return this.LongUser;
    }


    override fun addFavorite(subject: Subject) {

        try {

            val requestBody = PostFavoriteRequest(id, subject.id)
            Log.d("CACA",subject.id)
            val call = apiService.addFavorite(requestBody);

            call.enqueue(object : Callback<MessageResponse> {
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful){
                        Snackbar.make(binding.root.rootView, "Requête réussie", Snackbar.LENGTH_LONG).show()
                        var intent: Intent = Intent(binding.root.context,MainActivity::class.java);
                        // Vrai Id d'utilisateur
                        intent.putExtra("UserId",id);
                        startActivity(intent);
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

    override fun changePageToAddSubject() {
        val intent: Intent = Intent(this, AddSubjectPage::class.java).apply {
            // Ici on mets toutes les infos importantes
            putExtra("userId", id);
        }
        startActivity(intent);
    }

    private fun fetchLocation(){
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }
        task.addOnSuccessListener {
            if (it != null){
                LatUser = it.latitude.toFloat();
                LongUser = it.longitude.toFloat()
            }
        }
    }
}