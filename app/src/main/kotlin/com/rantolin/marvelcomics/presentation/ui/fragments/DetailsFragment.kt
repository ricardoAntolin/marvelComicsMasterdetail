package com.rantolin.marvelcomics.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rantolin.marvelcomics.domain.model.ComicModel
import com.rantolin.marvelcomics.presentation.R
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * Created by ricar on 9/4/17.
 */

const val COMIC_BUNDLE = "Comic_Bundle"


class DetailsFragment: BaseFragment(){

    var comicModel:ComicModel? = null
    var characterURL: String? = null

    companion object{
        fun newInstance (bundle: Bundle?): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readBundle()
        setTitle()
    }

    private fun setTitle() {
        activity.toolbar_layout?.title = comicModel?.title
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_details,container,false)
    }

    private fun readBundle() {
        val bundle: Bundle? = arguments
        comicModel = bundle?.getParcelable(COMIC_BUNDLE)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtDescription.text = comicModel?.description

    }
}