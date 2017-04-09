package com.rantolin.marvelcomics.domain.model

import android.os.Parcel
import android.os.Parcelable

data class ComicModel (var id: Int, var title: String, var description:String, var thumbnail: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ComicModel> = object : Parcelable.Creator<ComicModel> {
            override fun createFromParcel(source: Parcel): ComicModel = ComicModel(source)
            override fun newArray(size: Int): Array<ComicModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readString(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(title)
        dest?.writeString(description)
        dest?.writeString(thumbnail)
    }
}