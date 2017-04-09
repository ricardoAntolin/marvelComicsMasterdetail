package com.rantolin.marvelcomics.data.entity.mapper

import com.rantolin.marvelcomics.data.entity.ComicEntity
import com.rantolin.marvelcomics.domain.model.ComicModel

/**
 * Created by ricar on 1/4/17.
 */
class ComicEntityMapper : EntityMapper<ComicModel, ComicEntity>() {
    override fun transform(entity: ComicEntity): ComicModel {
        return ComicModel(entity.id,
                entity.title,
                entity.description ?: "No description available",
                "${entity.thumbnail.path}.${entity.thumbnail.extension}")
    }
}