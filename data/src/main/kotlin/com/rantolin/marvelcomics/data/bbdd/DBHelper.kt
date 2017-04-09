package com.rantolin.marvelcomics.data.bbdd


import com.rantolin.marvelcomics.data.entity.ComicEntity
import io.realm.Realm
import io.realm.RealmObject
import javax.inject.Inject

const val NO_DATA = "No data"
const val ERROR_SAVING_DATA = "Error saving data"

class DBHelper @Inject constructor() {
    private lateinit var realm: Realm

    fun initRealmTransaction() {
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
    }

    fun closeTransaction() {
        realm.commitTransaction()
        realm.close()
    }

    fun <T : RealmObject> addToRealm(entity: T) {
        initRealmTransaction()
        realm.copyToRealmOrUpdate(entity)
        closeTransaction()
    }

    fun <T : RealmObject> addToRealm(entity: List<T>) {
        initRealmTransaction()
        realm.copyToRealmOrUpdate(entity)
        closeTransaction()
    }
}
