package com.example.tbc_assign22.di

import com.example.tbc_assign22.data.remote.service.IGetPlacesService
import com.example.tbc_assign22.data.remote.service.IGetPostsService
import com.example.tbc_assign22.data.remote.util.HandleResponse
import com.example.tbc_assign22.data.repository.remote.GetPlacesRepositoryImpl
import com.example.tbc_assign22.data.repository.remote.GetPostsRepositoryImpl
import com.example.tbc_assign22.domain.repository.IGetPlacesRepository
import com.example.tbc_assign22.domain.repository.IGetPostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Provides
    @Singleton
    fun provideGetPlacesRepository(
        service: IGetPlacesService,
        handler: HandleResponse
    ): IGetPlacesRepository {
        return GetPlacesRepositoryImpl(service, handler)
    }

    @Provides
    @Singleton
    fun provideGetPostsRepository(
        service: IGetPostsService,
        handler: HandleResponse
    ): IGetPostsRepository {
        return GetPostsRepositoryImpl(service, handler)
    }
}