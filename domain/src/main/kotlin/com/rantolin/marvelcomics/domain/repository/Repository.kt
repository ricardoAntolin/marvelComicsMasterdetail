package com.rantolin.marvelcomics.domain.repository

import com.rantolin.marvelcomics.domain.model.CharacterModel
import com.rantolin.marvelcomics.domain.model.ComicModel
import io.reactivex.Observable


interface Repository {
    fun getComics(): Observable<List<ComicModel>>
    fun getCharacterDetails(): Observable<CharacterModel>
}