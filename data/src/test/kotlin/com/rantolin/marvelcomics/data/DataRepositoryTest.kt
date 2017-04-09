package com.rantolin.marvelcomics.data

import com.rantolin.marvelcomics.data.bbdd.DBHelper
import com.rantolin.marvelcomics.data.cloud.RestApi
import com.rantolin.marvelcomics.data.cloud.responsemodels.ComicsResponse
import com.rantolin.marvelcomics.data.cloud.responsemodels.DataEntity
import com.rantolin.marvelcomics.data.datasource.DataFactory
import com.rantolin.marvelcomics.data.entity.CharacterEntity
import com.rantolin.marvelcomics.data.entity.ComicEntity
import com.rantolin.marvelcomics.data.entity.ThumbnailEntity
import com.rantolin.marvelcomics.data.repository.DataRepository
import com.rantolin.marvelcomics.domain.model.CharacterModel
import com.rantolin.marvelcomics.domain.model.ComicModel
import de.jodamob.kotlin.testrunner.KotlinTestRunner
import de.jodamob.kotlin.testrunner.OpenedPackages
import io.reactivex.Observable
import org.amshove.kluent.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given

/**
 * Created by ricar on 9/4/17.
 */
@RunWith(KotlinTestRunner::class)
@OpenedPackages("com.rantolin.marvelcomics.data")
class DataRepositoryTest {

    private lateinit var dataRepository: DataRepository
    private lateinit var dataFactory: DataFactory

    private val mockRestApi = mock(RestApi::class)
    private val mockDBHelper = mock(DBHelper::class)

    @Before
    fun setup() {
        dataFactory = DataFactory(mockRestApi,mockDBHelper)
        dataRepository = DataRepository(dataFactory)

        given(mockRestApi.getComics()).willReturn(Observable.just(getComicListResponseMock()))
        given(mockRestApi.getCharacterDetails()).willReturn(Observable.just(getCharacterDetailsResponseMock()))
    }

    @Test
    fun testGetComicsCall(){
        val result = dataRepository.getComics()
        result.test().assertResult(getComicListModelResponseMock())
    }

    @Test
    fun testGetCharacterDetailsCall(){
        val result = dataRepository.getCharacterDetails()
        result.test().assertResult(getCharacterModelResponseMock())
    }

    private fun getComicListResponseMock():ComicsResponse<ComicEntity>{
        val comicList = ArrayList<ComicEntity>()
        comicList.add(ComicEntity(1,"Title","Description", ThumbnailEntity("http://image","jpg")))
        return ComicsResponse(DataEntity(comicList))
    }

    private fun getCharacterDetailsResponseMock():ComicsResponse<CharacterEntity>{
        val characterList = ArrayList<CharacterEntity>()
        characterList.add(CharacterEntity("Name","Description", ThumbnailEntity("http://image","jpg")))
        return ComicsResponse(DataEntity(characterList))
    }

    private fun getComicListModelResponseMock():List<ComicModel>{
        val comicList = ArrayList<ComicModel>()
        comicList.add(ComicModel(1,"Title","Description", "http://image.jpg"))
        return comicList
    }

    private fun getCharacterModelResponseMock():CharacterModel{
        return CharacterModel("Name","Description","http://image.jpg")
    }
}