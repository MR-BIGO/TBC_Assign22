package com.example.tbc_assign22.data.remote.service

import com.example.tbc_assign22.data.remote.model.PlaceDto
import retrofit2.Response
import retrofit2.http.GET

interface IGetPlacesService {

    @GET("1e2c42be-fc82-4efb-9f3f-4ce4ce80743c")
    suspend fun getPlaces(): Response<List<PlaceDto>>
}