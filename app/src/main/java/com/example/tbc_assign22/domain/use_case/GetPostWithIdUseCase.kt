package com.example.tbc_assign22.domain.use_case

import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.domain.model.Post
import com.example.tbc_assign22.domain.repository.IGetPostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostWithIdUseCase @Inject constructor(private val repository: IGetPostsRepository) {

    suspend operator fun invoke(id: Int): Flow<Resource<Post>> {
        return repository.getPostWithId(id)
    }
}