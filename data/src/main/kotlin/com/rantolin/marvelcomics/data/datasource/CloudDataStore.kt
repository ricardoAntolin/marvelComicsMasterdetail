package com.rantolin.marvelcomics.data.datasource

import com.rantolin.marvelcomics.data.bbdd.DBHelper
import com.rantolin.marvelcomics.data.cloud.RestApi
import com.rantolin.marvelcomics.data.cloud.responsemodels.ComicsResponse
import com.rantolin.marvelcomics.data.entity.CharacterEntity
import com.rantolin.marvelcomics.data.entity.ComicEntity
import io.reactivex.Observable

class CloudDataStore(private val restApi: RestApi, private var dbHelper: DBHelper) : DataStore {
    override fun getCharacterDetails(): Observable<ComicsResponse<CharacterEntity>> {
        return restApi.getCharacterDetails().doOnNext { dbHelper.addToRealm(it.data.results) }
    }

    override fun getComics(): Observable<ComicsResponse<ComicEntity>> {
        return restApi.getComics().doOnNext { dbHelper.addToRealm(it.data.results) }
    }

}