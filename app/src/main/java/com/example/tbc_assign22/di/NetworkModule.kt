package com.example.tbc_assign22.di

import com.squareup.moshi.Moshi
import com.example.tbc_assign22.BuildConfig
import com.example.tbc_assign22.data.remote.service.IGetPlacesService
import com.example.tbc_assign22.data.remote.service.IGetPostsService
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            // Set the log level to NONE when it's not a debug build
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        // Add the logging interceptor in debug mode
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    @Named("Places")
    fun providePlacesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
    }

    @Singleton
    @Provides
    @Named("Posts")
    fun providePostsRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
    }

    @Singleton
    @Provides
    fun provideGetPlacesService(@Named("Places") retrofit: Retrofit): IGetPlacesService {
        return retrofit.create(IGetPlacesService::class.java)
    }

    @Singleton
    @Provides
    fun provideGetPostsService(@Named("Posts") retrofit: Retrofit): IGetPostsService {
        return retrofit.create(IGetPostsService::class.java)
    }
}