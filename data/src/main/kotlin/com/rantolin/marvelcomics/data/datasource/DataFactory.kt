package com.rantolin.marvelcomics.data.datasource

import com.rantolin.marvelcomics.data.bbdd.DBHelper
import com.rantolin.marvelcomics.data.cloud.RestApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataFactory @Inject constructor(private val restApi: RestApi, val dbHelper: DBHelper) {

    fun createCloudDataStore(): DataStore {
        return CloudDataStore(restApi, dbHelper)
    }

    fun createDBDataStore(): DataStore {
        return DBDataStore(dbHelper)
    }

}

