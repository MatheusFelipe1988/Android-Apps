package com.example.projectocean

import retrofit2.Call
import retrofit2.http.GET

data class Item(val nome: String, val imagem: String)

interface RetroInter {
    // [GET] https://ocean-api-itens.onrender.com/itens
    @GET("itens")
    fun readingItens(): Call<Array<Item>>
}