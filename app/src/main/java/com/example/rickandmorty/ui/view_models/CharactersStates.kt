package com.example.csgoskins.ui.view_models

import com.example.csgoskins.domain.models.CharacterModel

data class CharactersStates(
    val load: Boolean = false,
    val success: List<CharacterModel> = emptyList(),
    val fail: String = ""
)
