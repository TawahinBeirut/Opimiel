package com.example.opimiel_frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opimiel_frontend.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class Home : AppCompatActivity()  {

    lateinit var id: String;
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(R.layout.home);
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView);


        // Initialisation des fragments
        val homeFragment = HomeFragment();
        val favoritesFragment = FavoritesFragment();
        val profileFragment = ProfileFragment();

        // Config du fragment de base
        setCurrentFragment(homeFragment);

        var intent: Intent = intent;
        id= intent.getStringExtra("UserId").toString();


        bottomNavigationView.setOnItemSelectedListener{
            item ->
            when(item.itemId){
                R.id.home_fragment -> setCurrentFragment(homeFragment)
                R.id.favorites_fragment -> setCurrentFragment(favoritesFragment)
                R.id.profile_fragment -> setCurrentFragment(profileFragment)
            }
            true
        }

    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.main_frame_layout,fragment);
                Log.d( "OE","Nae");
                commit();
            }
}