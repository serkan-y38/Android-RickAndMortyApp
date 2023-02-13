package com.example.csgoskins.data.api

import com.example.csgoskins.data.dto.Characters
import com.example.csgoskins.utils.Constants
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getCharacters(): Characters

}