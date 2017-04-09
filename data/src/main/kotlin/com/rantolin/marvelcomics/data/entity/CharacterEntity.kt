package com.rantolin.marvelcomics.data.entity

import io.realm.RealmObject

/**
 * Created by ricar on 9/4/17.
 */
open class CharacterEntity(
        var name:String,
        var description:String,
        var thumbnail: ThumbnailEntity): RealmObject(){

    constructor() : this("", "", ThumbnailEntity("", ""))
}