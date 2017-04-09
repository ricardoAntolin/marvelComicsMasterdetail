package com.rantolin.marvelcomics.data.cloud

import com.google.gson.Gson
import com.rantolin.marvelcomics.data.cloud.responsemodels.ComicsResponse
import com.rantolin.marvelcomics.data.entity.CharacterEntity
import com.rantolin.marvelcomics.data.entity.ComicEntity
import com.rantolin.marvelcomics.data.executor.JobExecutor
import com.rantolin.marvelcomics.data.utils.ToMD5
import com.rantolin.marvelcomics.data.utils.toTimeString
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

const val TS = "ts"
const val API_KEY = "apikey"
const val HASH = "hash"

const val PUBLIC_KEY = "71ee5dd583e759a39345110e0abced06"
const val PRIVATE_KEY = "604e7792a1ddc301bf50786d14d9e521064705a1"


@Singleton
class RestApi @Inject constructor() {
    private val BASE_URL = "https://gateway.marvel.com:443/v1/public/"

    private val service: ApiService

    init {
        val retro = Retrofit.Builder().baseUrl(BASE_URL)
                .callbackExecutor(JobExecutor())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(CustomOkhttpClient.getInstance()).build()
        service = retro.create(ApiService::class.java)
    }

    fun getComics(): Observable<ComicsResponse<ComicEntity>> {
        return service.getComics()
    }

    fun getCharacterDetails(): Observable<ComicsResponse<CharacterEntity>>{
        return service.getCharacterDetails()
    }
}
