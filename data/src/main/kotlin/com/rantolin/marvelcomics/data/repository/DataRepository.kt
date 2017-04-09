package com.rantolin.marvelcomics.data.repository

import com.rantolin.marvelcomics.data.datasource.DataFactory
import com.rantolin.marvelcomics.data.entity.mapper.CharacterEntityMapper
import com.rantolin.marvelcomics.data.entity.mapper.ComicEntityMapper
import com.rantolin.marvelcomics.domain.model.CharacterModel
import com.rantolin.marvelcomics.domain.model.ComicModel
import com.rantolin.marvelcomics.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class DataRepository @Inject constructor(private val dataFactory: DataFactory) : Repository {

    override fun getCharacterDetails(): Observable<CharacterModel> {
        return dataFactory.createCloudDataStore().getCharacterDetails().map {
            CharacterEntityMapper().transform(it.data.results).first()
        }
    }

    override fun getComics(): Observable<List<ComicModel>> {
        return dataFactory.createCloudDataStore().getComics().map {
            ComicEntityMapper().transform(it.data.results)
        }
    }

}