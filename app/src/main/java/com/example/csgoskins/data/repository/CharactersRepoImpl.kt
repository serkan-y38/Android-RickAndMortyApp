package com.example.csgoskins.data.repository

import com.example.csgoskins.data.api.ApiService
import com.example.csgoskins.data.dto.Characters
import com.example.csgoskins.domain.repositories.CharactersRepo
import javax.inject.Inject

class CharactersRepoImpl @Inject constructor(private val apiService: ApiService) : CharactersRepo {

    override suspend fun getCharacters(): Characters = apiService.getCharacters()

}