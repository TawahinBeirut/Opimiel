package com.example.opimiel_frontend.ui.profile

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
import com.example.opimiel_frontend.databinding.FragmentProfileBinding
import com.example.opimiel_frontend.model.listeners.ChangePageListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileFragment : Fragment() {

    private lateinit var addButton: FloatingActionButton;
    private var _binding: FragmentProfileBinding? = null

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

            addButton.setOnClickListener{
                Log.d("#oe","kdk")
                main.changePageToAddSubject()
            }
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}