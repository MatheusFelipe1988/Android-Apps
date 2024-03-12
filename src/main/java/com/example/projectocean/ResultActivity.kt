package com.example.projectocean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val btReturn = findViewById<Button>(R.id.btReturn)

        val digiteNname = intent.getStringExtra("NAME_DIGITED")

        val tvResult = findViewById<TextView>(R.id.tvResult)

        tvResult.text = digiteNname


        btReturn.setOnClickListener {
            finish()
        }
    }
}