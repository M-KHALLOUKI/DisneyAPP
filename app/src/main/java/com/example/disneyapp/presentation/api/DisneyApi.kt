package com.example.disneyapp.presentation.api

import retrofit2.Call
import retrofit2.http.GET



interface DisneyApi {
    @GET("/characters")
    fun getDisneyList():Call<DisneyResponse>


}