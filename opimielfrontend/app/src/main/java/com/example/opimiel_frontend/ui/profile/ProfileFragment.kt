package com.example.opimiel_frontend.ui.profile

import ApiService
import OnSubjectClickListener
import SubjectsResponse
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.opimiel_frontend.AddSubjectPage
import com.example.opimiel_frontend.HomeAdapter
import com.example.opimiel_frontend.Subject
import com.example.opimiel_frontend.databinding.FragmentProfileBinding
import com.example.opimiel_frontend.model.listeners.ChangePageListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment : Fragment() {

    private lateinit var addButton: Button;
    private var _binding: FragmentProfileBinding? = null
    private lateinit var homeAdapter: HomeAdapter;

    val retrofit = Retrofit.Builder()
        .baseUrl("https://opimiel.vercel.app/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(ApiService::class.java)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding =FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Configuration du Home Adaptater

        addButton = binding.fab;


        val textView: TextView = binding.textViewProfile
        profileViewModel.text.observe(viewLifecycleOwner) {

            val main = requireActivity() as ChangePageListener;
            textView.text = it


        }

        // Configuration du Home Adaptater
        with(binding.recyclerViewProfile) {
            layoutManager = LinearLayoutManager(context)
            // On met l'api ici

            adapter = HomeAdapter(requireActivity() as OnSubjectClickListener);
            homeAdapter = adapter as HomeAdapter;
            addButton.setOnClickListener{
                Log.d("#oe","kdk")
                (context as ChangePageListener).changePageToAddSubject()
            }
        }
        Log.d("oe",(requireActivity() as OnSubjectClickListener).getUserId())
        // appel api
        apiService.getOwnSubjects((requireActivity() as OnSubjectClickListener).getUserId()).enqueue(object : Callback<SubjectsResponse> {
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
}