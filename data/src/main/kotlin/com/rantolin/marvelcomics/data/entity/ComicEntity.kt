package com.rantolin.marvelcomics.data.entity

import io.realm.RealmObject

/**
 * Created by ricar on 8/4/17.
 */

open class ComicEntity (var id: Int, var title: String, var description: String?, var thumbnail: ThumbnailEntity): RealmObject(){
    constructor() : this(0,"", "", ThumbnailEntity("",""))
}


