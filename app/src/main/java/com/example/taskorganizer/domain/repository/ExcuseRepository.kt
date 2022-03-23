package com.example.taskorganizer.domain.repository

import com.example.taskorganizer.domain.models.Excuse
import retrofit2.Response

interface ExcuseRepository {
    suspend fun getExcuse(): Response<Excuse>
}