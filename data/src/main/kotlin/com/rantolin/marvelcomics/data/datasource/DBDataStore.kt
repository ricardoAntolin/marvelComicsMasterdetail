package com.rantolin.marvelcomics.data.datasource


import com.rantolin.marvelcomics.data.bbdd.DBHelper
import com.rantolin.marvelcomics.data.cloud.responsemodels.ComicsResponse
import com.rantolin.marvelcomics.data.entity.CharacterEntity
import com.rantolin.marvelcomics.data.entity.ComicEntity
import io.reactivex.Observable


class DBDataStore(private val dbHelper: DBHelper) : DataStore {
    override fun getCharacterDetails(): Observable<ComicsResponse<CharacterEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getComics(): Observable<ComicsResponse<ComicEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}