package com.example.tbc_assign22.domain.use_case

import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.domain.model.Place
import com.example.tbc_assign22.domain.repository.IGetPlacesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val repository: IGetPlacesRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Place>>> {
        return repository.getPlaces()
    }
}