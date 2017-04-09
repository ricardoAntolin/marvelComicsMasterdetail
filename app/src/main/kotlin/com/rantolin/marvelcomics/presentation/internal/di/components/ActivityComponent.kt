package com.rantolin.marvelcomics.presentation.internal.di.components


import com.rantolin.marvelcomics.presentation.internal.di.modules.ActivityModule
import com.rantolin.marvelcomics.presentation.internal.di.scope.PerActivity
import com.rantolin.marvelcomics.presentation.ui.activities.DetailsActivity
import com.rantolin.marvelcomics.presentation.ui.activities.MainActivity
import dagger.Component

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(detailsActivity: DetailsActivity)
}
