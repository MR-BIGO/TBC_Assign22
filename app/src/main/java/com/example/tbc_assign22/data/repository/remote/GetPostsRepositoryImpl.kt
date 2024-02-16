package com.example.tbc_assign22.data.repository.remote

import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.data.remote.mapper.mapListToDomain
import com.example.tbc_assign22.data.remote.mapper.mapToDomain
import com.example.tbc_assign22.data.remote.mapper.toDomain
import com.example.tbc_assign22.data.remote.service.IGetPostsService
import com.example.tbc_assign22.data.remote.util.HandleResponse
import com.example.tbc_assign22.domain.model.Post
import com.example.tbc_assign22.domain.repository.IGetPostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsRepositoryImpl @Inject constructor(
    private val service: IGetPostsService,
    private val handler: HandleResponse
) : IGetPostsRepository {
    override suspend fun getPosts(): Flow<Resource<List<Post>>> {
        return handler.safeApiCall { service.getPosts() }.mapListToDomain { it.toDomain() }

    }

    override suspend fun getPostWithId(id: Int): Flow<Resource<Post>> {
        return handler.safeApiCall { service.getPostWithId(id) }.mapToDomain { it.toDomain() }
    }
}