
package com.rantolin.marvelcomics.presentation.ui.presenters

import com.rantolin.marvelcomics.domain.model.ComicModel
import com.rantolin.marvelcomics.domain.usecases.DefaultObserver
import com.rantolin.marvelcomics.domain.usecases.GetComicsUseCase

import com.rantolin.marvelcomics.presentation.internal.di.scope.PerFragment
import com.rantolin.marvelcomics.presentation.ui.views.MainView
import javax.inject.Inject

@PerFragment
class MainPresenter @Inject
constructor(val getComicsUseCase: GetComicsUseCase) {

    var view: MainView? = null

    fun onCreate(view: MainView) {
        this.view = view
        view.showLoading()
        getComicsUseCase.execute(UserListObserver(), null)
    }

    inner class UserListObserver: DefaultObserver<List<ComicModel>>() {
        override fun onComplete() { }

        override fun onNext(t: List<ComicModel>) {
            this@MainPresenter.view?.hideLoading()
            this@MainPresenter.view?.searchResult(t)
        }

        override fun onError(exception: Throwable) {
            this@MainPresenter.view?.hideLoading()
            this@MainPresenter.view?.showError(exception)
        }

    }
}

