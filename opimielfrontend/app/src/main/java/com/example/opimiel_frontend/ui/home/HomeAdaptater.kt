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
    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_EMPTY = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (subjectList.isNullOrEmpty()) TYPE_EMPTY else TYPE_ITEM
    }

    class ViewHolder(view: View,viewType: Int) : RecyclerView.ViewHolder(view) {
        lateinit var textView: TextView
        lateinit var participateButton : Button;


        init {
            // Define click listener for the ViewHolder's View
            if (viewType == TYPE_ITEM) {
                textView = view.findViewById(R.id.text_view_item)
                participateButton = view.findViewById(R.id.button_subject_switch);
            }
            else{
                textView = view.findViewById(R.id.text_empty_view)
                textView.text = "Pas de sujets disponibles"
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == TYPE_ITEM) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            ViewHolder(itemView,viewType)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.empty_view, parent, false)
            ViewHolder(itemView,viewType)
        }
    }

    override fun getItemCount(): Int {
        return if (subjectList.isEmpty()) 1 else subjectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ITEM) {
            val currentSubject = subjectList[position];
            holder.textView.text = currentSubject.name;
            // completer les champs de la view donner

            holder.participateButton.setOnClickListener {
                listener.onSubjectClick(currentSubject)
            }
        }else{
            holder.textView.text = "Pas de sujets dispos"
        }
    }
    fun updateSubjects(newSubjects: List<Subject>) {
        subjectList.clear()
        subjectList.addAll(newSubjects)
        notifyDataSetChanged()
    }
}