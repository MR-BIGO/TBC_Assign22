package com.example.tbc_assign22.domain.use_case

import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.domain.model.Post
import com.example.tbc_assign22.domain.repository.IGetPostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: IGetPostsRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Post>>> {
        return repository.getPosts()
    }
}