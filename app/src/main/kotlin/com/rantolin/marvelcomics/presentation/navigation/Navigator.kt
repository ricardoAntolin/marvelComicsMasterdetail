package com.rantolin.marvelcomics.presentation.navigation

import android.app.Activity
import android.os.Bundle
import com.rantolin.marvelcomics.presentation.ui.activities.DetailsActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ricar on 9/4/17.
 */

@Singleton
class Navigator
@Inject
constructor(){


    fun goToDetailsActivity(originActivity: Activity, extras: Bundle){
        originActivity.navigateToActivity(DetailsActivity::class.java,extras,false,0,0)
    }
}