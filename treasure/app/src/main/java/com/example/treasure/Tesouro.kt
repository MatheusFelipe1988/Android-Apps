package com.example.treasure

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Tesouro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tesouro_activity)

        val keySpeak = intent.getStringExtra("PALAVRA_CHAVE")

        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val ivTreasure = findViewById<ImageView>(R.id.ivTreasure)
        var messageTreasure = "Continue tentando"
        var TreasureImage = R.drawable.bau_vazio

        when(keySpeak){
            "ouro" -> {
                TreasureImage = R.drawable.ouro
                messageTreasure = "Parabéns, você encontrou o ouro!"
            }
            "prata" -> {
                TreasureImage = R.drawable.prata
                messageTreasure = "Parabéns, você encontrou a prata!"
            }
            "bronze" -> {
                TreasureImage = R.drawable.bronze
                messageTreasure = "Parabéns, você encontrou o bronze!"
            }
        }

        ivTreasure.setImageResource(TreasureImage)
        tvMessage.text = messageTreasure

        val btVoltar = findViewById<Button>(R.id.btVoltar)
        btVoltar.setOnClickListener {
            finish()
        }
    }
}