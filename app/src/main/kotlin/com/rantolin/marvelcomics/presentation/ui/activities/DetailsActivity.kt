package com.rantolin.marvelcomics.presentation.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rantolin.marvelcomics.domain.model.ComicModel
import com.rantolin.marvelcomics.presentation.AndroidApplication
import com.rantolin.marvelcomics.presentation.R
import com.rantolin.marvelcomics.presentation.internal.di.components.ActivityComponent
import com.rantolin.marvelcomics.presentation.internal.di.components.DaggerActivityComponent
import com.rantolin.marvelcomics.presentation.internal.di.modules.ActivityModule
import com.rantolin.marvelcomics.presentation.navigation.navigateToFragment
import com.rantolin.marvelcomics.presentation.ui.fragments.COMIC_BUNDLE
import com.rantolin.marvelcomics.presentation.ui.fragments.DetailsFragment
import com.rantolin.marvelcomics.utils.loadUrl
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Created by ricar on 9/4/17.
 */

const val COMIC_EXTRAS = "comic_extras"
const val CHARACTER_URL = "Character_url"

class DetailsActivity:AppCompatActivity() {
    lateinit var comicModel:ComicModel
    lateinit var characterURL: String

    private val component: ActivityComponent
        get() = DaggerActivityComponent.builder()
                .applicationComponent((application as AndroidApplication).component)
                .activityModule(ActivityModule(this))
                .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        component.inject(this)

        comicModel = intent.extras.getParcelable<ComicModel>(COMIC_EXTRAS)
        characterURL = intent.extras.getString(CHARACTER_URL)

        setSupportActionBar(detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        inflateMainFragment()

        setImages()

    }

    private fun setImages() {
        profileImage.loadUrl(comicModel.thumbnail)
        appBarImage.loadUrl(comicModel.thumbnail)
        profileImage.loadUrl(characterURL)
    }

    fun inflateMainFragment(){
        val bundle = Bundle()
        bundle.putParcelable(COMIC_BUNDLE,comicModel)

        navigateToFragment(DetailsFragment.newInstance(bundle), R.id.detailsContainer,false)
    }
}