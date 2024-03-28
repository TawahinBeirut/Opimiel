package com.example.opimiel_frontend

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SubjectPage: AppCompatActivity(),OnMapReadyCallback {

    private lateinit var userId: String;
    private lateinit var name: String;
    private lateinit var subjectId: String;
    private lateinit var authorId: String;


    private lateinit var mMap: GoogleMap

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

        val locationList :MutableList<LatLng> = mutableListOf(LatLng(46.2276, 2.2137),LatLng(48.0, 3.0));
        // Exemple: Ajoute un marker à Sydney et déplace la caméra

        locationList.forEach{
            location ->
            kotlin.run {
                // On recuperera des vrai truc de la liste --> a voir
                val sydney = location
                // On regle l'icone ( couleur ) en fonction
                mMap.addMarker(MarkerOptions().position(sydney).title("Marker qui depend de la reponse"))

            }
            val france = LatLng(46.2276, 2.2137)
            // Centrer la carte sur la France
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france, 5.5f))
        }
    }
}