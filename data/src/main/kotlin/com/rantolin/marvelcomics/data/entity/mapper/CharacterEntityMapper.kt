package com.rantolin.marvelcomics.data.entity.mapper

import com.rantolin.marvelcomics.data.entity.CharacterEntity
import com.rantolin.marvelcomics.domain.model.CharacterModel

/**
 * Created by ricar on 9/4/17.
 */
class CharacterEntityMapper: EntityMapper<CharacterModel,CharacterEntity>(){
    override fun transform(entity: CharacterEntity): CharacterModel {
        return CharacterModel(
                entity.name,
                entity.description,
                "${entity.thumbnail.path}.${entity.thumbnail.extension}"
        )
    }

}