package com.miguelzaragozaserrano.marvel.utils.extensions

import com.miguelzaragozaserrano.data.models.response.Character
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.extensions.orEmpty
import com.miguelzaragozaserrano.marvel.models.CharacterView
import com.miguelzaragozaserrano.marvel.models.CharactersView

fun Character.toCharacterView(): CharacterView = CharacterView(
    id = id.orEmpty(),
    name = name.orEmpty(),
    description = description.orEmpty(),
    modified = modified,
    resourceURI = resourceURI.orEmpty(),
    favorite = null,
    image = thumbnail?.image().orEmpty()
)

fun Characters.toCharactersView(): CharactersView = CharactersView(
    offset.orEmpty(),
    results?.map { it.toCharacterView() }?.toMutableList().orEmpty()
)