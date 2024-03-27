package com.example.opimiel_frontend

import OnSubjectClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val listener: OnSubjectClickListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private var subjectList: MutableList<Subject> = mutableListOf();
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        lateinit var participateButton : Button;

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.text_view_item)
            participateButton = view.findViewById(R.id.button_subject_switch);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false);
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return subjectList.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSubject = subjectList[position];
        holder.textView.text = currentSubject.name;
        // completer les champs de la view donner

        holder.participateButton.setOnClickListener {
            listener.onSubjectClick(currentSubject)
        }
    }
    fun updateSubjects(newSubjects: List<Subject>) {
        subjectList.clear()
        subjectList.addAll(newSubjects)
        notifyDataSetChanged()
    }
}