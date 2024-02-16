package com.example.tbc_assign22.data.remote.service

import com.example.tbc_assign22.data.remote.model.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IGetPostsService {

    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPosts(): Response<List<PostDto>>

    @GET("d02b76bb-095d-45fa-90e1-dc4733d1f247/{id}")
    suspend fun getPostWithId(@Path("id") id: Int): Response<PostDto>
}