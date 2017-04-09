package com.rantolin.marvelcomics.presentation.ui.views

import com.rantolin.marvelcomics.domain.model.ComicModel


interface MainView: BaseView {
    fun searchResult(entryList:List<ComicModel>)
    fun showLoading()
    fun hideLoading()
}