package com.example.tbc_assign22.data.remote.service

import com.example.tbc_assign22.data.remote.model.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface IGetPostsService {

    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPosts(): Response<List<PostDto>>
}