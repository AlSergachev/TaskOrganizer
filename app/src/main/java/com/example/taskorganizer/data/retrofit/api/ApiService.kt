package com.example.taskorganizer.data.retrofit.api

import com.example.taskorganizer.domain.models.Excuse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v1/excuse")
    suspend fun getExcuse(): Response<Excuse>
}