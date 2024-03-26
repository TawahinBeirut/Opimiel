package com.example.opimiel_frontend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment: Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Trouver le RecyclerView dans le layout du Fragment
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        // Définir le LayoutManager pour le RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Définir l'adaptateur pour le RecyclerView
        recyclerView.adapter = HomeAdapter()
    }
}