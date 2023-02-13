package com.example.csgoskins.domain.usecases

import com.example.csgoskins.domain.models.CharacterModel
import com.example.csgoskins.domain.models.toDomain
import com.example.csgoskins.domain.repositories.CharactersRepo
import com.example.csgoskins.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersUseCase @Inject constructor(private val repo: CharactersRepo) {
    suspend operator fun invoke(): Flow<Resource<List<CharacterModel>>> = flow {

        try {
            emit(Resource.Loading())
            val data = repo.getCharacters().results.map {
                it.toDomain()
            }
            emit(Resource.Success(data))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "no internet connection"))

        } catch (e: IOException) {
            emit(Resource.Error("error"))

        }

    }

}