package com.rantolin.marvelcomics.presentation.ui.views

import com.rantolin.marvelcomics.domain.model.CharacterModel

/**
 * Created by ricar on 9/4/17.
 */
interface DrawerView : BaseView{
    fun fillDrawer(characterModel: CharacterModel)
}