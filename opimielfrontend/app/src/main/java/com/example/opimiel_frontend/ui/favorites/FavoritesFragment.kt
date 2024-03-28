package com.example.opimiel_frontend.ui.favorites

import ApiService
import OnSubjectClickListener
import SubjectsResponse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.opimiel_frontend.HomeAdapter
import com.example.opimiel_frontend.Subject
import com.example.opimiel_frontend.databinding.FragmentFavoritesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoritesFragment : Fragment(),OnSubjectClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var homeAdapter: HomeAdapter;
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val retrofit = Retrofit.Builder()
        .baseUrl("https://opimiel.vercel.app/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(ApiService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textViewFavorites
        favoritesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Configuration du Home Adaptater
        with(binding.recyclerViewFavorites) {
            layoutManager = LinearLayoutManager(context)
            // On met l'api ici

            adapter = HomeAdapter(requireActivity() as OnSubjectClickListener);
            homeAdapter = adapter as HomeAdapter;
        }


        apiService.getFavorites((requireActivity() as OnSubjectClickListener).getUserId()).enqueue(object :
            Callback<SubjectsResponse> {
            override fun onResponse(call: Call<SubjectsResponse>, response: Response<SubjectsResponse>) {
                if (response.isSuccessful) {
                    val body = response.body();
                    val subjectList: MutableList<Subject> = mutableListOf();
                    subjectList.clear();
                    body?.data?.forEach { subject ->
                        run {
                            subjectList.add(subject);
                        }
                    }
                    homeAdapter.updateSubjects(subjectList);
                    Log.d("retussite api",subjectList.toString())
                } else {
                    Log.d("Erreur api","oe")
                }
            }

            override fun onFailure(call: Call<SubjectsResponse>, t: Throwable) {
                // Gérer les erreurs de connexion
                Log.d("Erreur connexion",t.toString())
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSubjectClick(subject: Subject) {
        TODO("Not yet implemented")
    }

    override fun getUserId(): String {
        TODO("Not yet implemented")
    }

    override fun getFragmentName(): String {
        return "Favorites"
    }

    override fun addFavorite(subject: Subject) {
        TODO("Not yet implemented")
    }

    override fun deleteFavorite(subject: Subject) {
        TODO("Not yet implemented")
    }
}