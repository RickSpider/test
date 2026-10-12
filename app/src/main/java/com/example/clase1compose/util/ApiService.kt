package com.example.clase1compose.util

import com.example.clase1compose.model.Data
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts/{id}")
    suspend fun getData(
        @Path("id") id: Int
    ): Data


}