package com.rantolin.marvelcomics.presentation.ui.presenters

import com.rantolin.marvelcomics.domain.model.CharacterModel
import com.rantolin.marvelcomics.domain.model.ComicModel
import com.rantolin.marvelcomics.domain.usecases.DefaultObserver
import com.rantolin.marvelcomics.domain.usecases.GetCharacterDetailsUseCase
import com.rantolin.marvelcomics.presentation.internal.di.scope.PerActivity
import com.rantolin.marvelcomics.presentation.ui.views.DrawerView
import javax.inject.Inject

/**
 * Created by ricar on 9/4/17.
 */
@PerActivity
class DrawerPresenter @Inject
constructor(val getCharacterDetailsUseCase: GetCharacterDetailsUseCase) {
    var view: DrawerView? = null

    fun onCreate(view: DrawerView) {
        this.view = view
        getCharacterDetailsUseCase.execute(CharacterDetailsObserver(), null)
    }

    inner class CharacterDetailsObserver: DefaultObserver<CharacterModel>() {
        override fun onComplete() { }

        override fun onNext(t: CharacterModel) { this@DrawerPresenter.view?.fillDrawer(t) }

        override fun onError(exception: Throwable) { this@DrawerPresenter.view?.showError(exception) }

    }
}