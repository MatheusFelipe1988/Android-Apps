package com.example.treasure

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etKey = findViewById<EditText>(R.id.etKey)
        val btProcura = findViewById<Button>(R.id.btProcura)

        btProcura.setOnClickListener {
            val chave = etKey.text.toString()

            if(chave.isNotBlank()){
                etKey.error = "Digite o termo"
            }else{
            val tesouroIntent = Intent(this, Tesouro::class.java)

            tesouroIntent.putExtra("PALAVRA_CHAVE", chave)
                startActivity(tesouroIntent)
            }
        }
    }
 }