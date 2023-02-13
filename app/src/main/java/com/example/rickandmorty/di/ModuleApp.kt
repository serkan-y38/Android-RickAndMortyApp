package com.example.csgoskins.di

import com.example.csgoskins.data.api.ApiService
import com.example.csgoskins.data.repository.CharactersRepoImpl
import com.example.csgoskins.domain.repositories.CharactersRepo
import com.example.csgoskins.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRepository(apiService:ApiService): CharactersRepo = CharactersRepoImpl(apiService)


}