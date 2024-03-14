package com.example.projectocean

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.security.auth.callback.CallbackHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://ocean-api-itens.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrointer = retrofit.create(RetroInter::class.java)
        retrointer.readingItens().enqueue(object : Callback<Array<Item>>{
            override fun onResponse(call: Call<Array<Item>>, response: Response<Array<Item>>) {
                response.body()?.let {
                    Log.d("API", it.size.toString())
                    it.forEach {
                        Log.d("API","${it.imagem} - ${it.imagem}")
                    }
                }
            }

            override fun onFailure(call: Call<Array<Item>>, t: Throwable) {
                Log.e("API", "Mensagem de erro",t)
            }
        })

    }
}