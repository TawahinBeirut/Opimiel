package com.example.opimiel_frontend

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubjectPage: AppCompatActivity() {

    private lateinit var userId: String;
    private lateinit var name: String;
    private lateinit var subjectId: String;
    private lateinit var authorId: String;

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
    }
}