package com.example.tbc_assign22.data.repository.remote

import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.data.remote.mapper.mapListToDomain
import com.example.tbc_assign22.data.remote.mapper.toDomain
import com.example.tbc_assign22.data.remote.service.IGetPlacesService
import com.example.tbc_assign22.data.remote.util.HandleResponse
import com.example.tbc_assign22.domain.model.Place
import com.example.tbc_assign22.domain.repository.IGetPlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlacesRepositoryImpl @Inject constructor(
    private val service: IGetPlacesService,
    private val handler: HandleResponse
) : IGetPlacesRepository {
    override suspend fun getPlaces(): Flow<Resource<List<Place>>> {
        return handler.safeApiCall { service.getPlaces() }.mapListToDomain { it.toDomain() }

    }
}