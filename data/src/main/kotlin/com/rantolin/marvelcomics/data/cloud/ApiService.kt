package com.rantolin.marvelcomics.data.cloud

import com.rantolin.marvelcomics.data.cloud.responsemodels.ComicsResponse
import com.rantolin.marvelcomics.data.entity.CharacterEntity
import com.rantolin.marvelcomics.data.entity.ComicEntity
import retrofit2.http.Query
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiService {
    @GET("characters/1009220/comics")
    fun getComics(): Observable<ComicsResponse<ComicEntity>>

    @GET("characters/1009220/comics")
    fun getComicDetails(): Observable<ComicsResponse<ComicEntity>>

    @GET("characters/1009220")
    fun getCharacterDetails(): Observable<ComicsResponse<CharacterEntity>>
}