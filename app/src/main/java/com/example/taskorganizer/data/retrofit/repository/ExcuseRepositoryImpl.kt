package com.example.taskorganizer.data.retrofit.repository

import com.example.taskorganizer.data.retrofit.api.RetrofitInstance
import com.example.taskorganizer.domain.models.Excuse
import com.example.taskorganizer.domain.repository.ExcuseRepository
import retrofit2.Response

class ExcuseRepositoryImpl(): ExcuseRepository {

    override suspend fun getExcuse(): Response<Excuse> {
        return RetrofitInstance.api.getExcuse()
    }

}