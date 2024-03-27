package com.example.opimiel_frontend

import OnSubjectClickListener
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.opimiel_frontend.databinding.ActivityMainBinding
import com.example.opimiel_frontend.model.listeners.ChangePageListener

class MainActivity : AppCompatActivity(),OnSubjectClickListener,ChangePageListener {

    private lateinit var id:String
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        var intent: Intent = intent;
        id= intent.getStringExtra("UserId").toString();
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


    override fun changePageToAddSubject() {
        val intent: Intent = Intent(this, AddSubjectPage::class.java).apply {
            // Ici on mets toutes les infos importantes
            putExtra("userId", id);
        }
        startActivity(intent);
    }
}