package com.example.opimiel_frontend

import ApiService
import PostResResponse
import SubjectsResponse
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SubjectPage: AppCompatActivity(),OnMapReadyCallback {

    private lateinit var userId: String;
    private lateinit var name: String;
    private lateinit var subjectId: String;
    private lateinit var authorId: String;


    private lateinit var mMap: GoogleMap;

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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subject_page);


        var intent: Intent = intent;
        userId= intent.getStringExtra("userId").toString();
        name = intent.getStringExtra("name").toString();
        subjectId= intent.getStringExtra("subjectId").toString();
        authorId= intent.getStringExtra("authorId").toString();

        


        val text :TextView = findViewById(R.id.textViewSubject);
        text.text = userId +" "+ name +" "+ subjectId +" "+ authorId;

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        // appel api
        apiService.getResponses(subjectId).enqueue(object : Callback<PostResResponse> {
            override fun onResponse(call: Call<PostResResponse>, response: Response<PostResResponse>) {
                if (response.isSuccessful) {
                    val body = response.body();
                    val resList: MutableList<LatLng> = mutableListOf();
                    body?.data?.forEach { subject ->
                        run {
                            if (subject.value) {
                                mMap
                                    .addMarker(
                                        MarkerOptions()
                                            .position(
                                                LatLng(
                                                    subject.latitude.toDouble(),
                                                    subject.longitude.toDouble()
                                                )
                                            )
                                            .title("Marker qui depend de la reponse")
                                            .icon(
                                                BitmapDescriptorFactory.defaultMarker(
                                                    BitmapDescriptorFactory.HUE_GREEN
                                                )
                                            )
                                    )
                            }
                            else {
                                mMap
                                    .addMarker(
                                        MarkerOptions()
                                            .position(
                                                LatLng(
                                                    subject.latitude.toDouble(),
                                                    subject.longitude.toDouble()
                                                )
                                            )
                                            .title("Marker qui depend de la reponse")
                                            .icon(
                                                BitmapDescriptorFactory.defaultMarker(
                                                    BitmapDescriptorFactory.HUE_RED
                                                )
                                            )
                                    )
                            }
                        }
                    }
                    Log.d("retussite api",resList.toString())
                } else {
                    Log.d("Erreur api","oe")
                }
            }

            override fun onFailure(call: Call<PostResResponse>, t: Throwable) {
                // Gérer les erreurs de connexion
                Log.d("Erreur connexion",t.toString())
            }
        })

        val france = LatLng(46.2276, 2.2137)
        // Centrer la carte sur la France
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france, 5.5f))
    }

}