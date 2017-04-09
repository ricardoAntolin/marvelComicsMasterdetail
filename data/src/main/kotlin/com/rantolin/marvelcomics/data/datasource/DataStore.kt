package com.rantolin.marvelcomics.data.datasource

import com.rantolin.marvelcomics.data.cloud.responsemodels.ComicsResponse
import com.rantolin.marvelcomics.data.entity.CharacterEntity
import com.rantolin.marvelcomics.data.entity.ComicEntity
import io.reactivex.Observable


interface DataStore {
    fun getComics(): Observable<ComicsResponse<ComicEntity>>
    fun getCharacterDetails(): Observable<ComicsResponse<CharacterEntity>>
}
