package com.example.csgoskins.domain.models
import android.os.Parcelable
import com.example.csgoskins.data.dto.Result
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
): Parcelable

fun Result.toDomain() = CharacterModel(
    created = created,
    episode = episode,
    gender = gender,
    id = id,
    image = image,
    name = name,
    species = species,
    status = status,
    type = type,
    url = url
)