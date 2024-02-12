package com.example.tbc_assign22.domain.repository

import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface IGetPostsRepository {

    suspend fun getPosts(): Flow<Resource<List<Post>>>
}