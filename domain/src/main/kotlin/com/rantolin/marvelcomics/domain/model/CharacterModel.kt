package com.rantolin.marvelcomics.domain.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ricar on 9/4/17.
 */
data class CharacterModel (var name: String, var description: String, var thumbnail: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CharacterModel> = object : Parcelable.Creator<CharacterModel> {
            override fun createFromParcel(source: Parcel): CharacterModel = CharacterModel(source)
            override fun newArray(size: Int): Array<CharacterModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(description)
        dest?.writeString(thumbnail)
    }
}