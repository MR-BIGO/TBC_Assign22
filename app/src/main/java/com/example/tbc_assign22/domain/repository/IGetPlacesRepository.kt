package com.example.tbc_assign22.domain.repository

import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface IGetPlacesRepository {

    suspend fun getPlaces(): Flow<Resource<List<Place>>>
}