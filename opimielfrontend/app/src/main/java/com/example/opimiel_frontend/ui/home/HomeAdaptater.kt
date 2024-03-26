package com.example.opimiel_frontend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(): RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private var subjectList: MutableList<Subject> = mutableListOf(
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"),
        Subject("oe","nan","oe"));


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.text_view_item)
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
        val currentObject = subjectList[position];
        holder.textView.text = currentObject.name;
        // completer les champs de la view donner
    }
}