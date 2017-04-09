package com.rantolin.marvelcomics.data.entity

import io.realm.RealmObject

/**
 * Created by ricar on 8/4/17.
 */
open class ThumbnailEntity (var path:String, var extension:String): RealmObject(){
    constructor() : this("","")
}