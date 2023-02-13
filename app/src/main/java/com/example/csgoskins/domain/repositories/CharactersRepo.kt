package com.example.csgoskins.domain.repositories

import com.example.csgoskins.data.dto.Characters

interface CharactersRepo {

    suspend fun getCharacters(): Characters

}