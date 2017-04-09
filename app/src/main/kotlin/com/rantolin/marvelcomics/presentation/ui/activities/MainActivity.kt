package com.rantolin.marvelcomics.presentation.ui.activities

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.rantolin.marvelcomics.domain.model.CharacterModel
import com.rantolin.marvelcomics.presentation.AndroidApplication
import com.rantolin.marvelcomics.presentation.R
import com.rantolin.marvelcomics.presentation.internal.di.components.ActivityComponent
import com.rantolin.marvelcomics.presentation.internal.di.components.DaggerActivityComponent
import com.rantolin.marvelcomics.presentation.internal.di.modules.ActivityModule
import com.rantolin.marvelcomics.presentation.navigation.Navigator
import com.rantolin.marvelcomics.presentation.navigation.navigateToFragment
import com.rantolin.marvelcomics.presentation.ui.fragments.MainFragment
import com.rantolin.marvelcomics.presentation.ui.presenters.DrawerPresenter
import com.rantolin.marvelcomics.presentation.ui.views.DrawerView
import com.rantolin.marvelcomics.utils.loadUrl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), DrawerView {

    var characterModel: CharacterModel? = null
    var fragment = MainFragment.newInstance(null)

    @Inject
    lateinit var drawerPresenter: DrawerPresenter
    @Inject
    lateinit var navigation: Navigator


    private val component: ActivityComponent
        get() = DaggerActivityComponent.builder()
                .applicationComponent((application as AndroidApplication).component)
                .activityModule(ActivityModule(this))
                .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        drawerPresenter.onCreate(this)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        inflateMainFragment()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun inflateMainFragment(){
        navigateToFragment(fragment, R.id.fragmentContainer,false)
    }

    override fun showError(throwable: Throwable) {

    }

    override fun fillDrawer(characterModel: CharacterModel) {
        this.characterModel = characterModel
        with(drawerLayout.navView){
            characterAvatar.loadUrl(characterModel.thumbnail)
            characterName.text = characterModel.name
            characterDetails.text = characterModel.description
        }
    }

    fun navigateToDetailsActivity(bundle:Bundle){
        bundle.putString(CHARACTER_URL,characterModel?.thumbnail)
        navigation.goToDetailsActivity(this,bundle)
    }
}
