package com.example.projectocean

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btPost = findViewById<Button>(R.id.btPost)

        var tvTextView = findViewById<TextView>(R.id.tvText)

        val editName = findViewById<EditText>(R.id.editName)



        btPost.setOnClickListener {

            if (editName.text.isNotBlank()) {
                tvTextView.text = editName.text

            } else {
                editName.error = "erro "
            }
        }

        val btOpenWindows = findViewById<Button>(R.id.btOpenWindow)
        btOpenWindows.setOnClickListener {
            val newWindowIntent = Intent(this, ResultActivity::class.java)

            newWindowIntent.putExtra("NAME_DIGITED", editName.text.toString())

            startActivity(newWindowIntent)
        }

    }
}