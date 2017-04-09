package com.rantolin.marvelcomics

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.azimolabs.conditionwatcher.ConditionWatcher
import com.rantolin.marvelcomics.presentation.R
import com.rantolin.marvelcomics.presentation.ui.activities.DetailsActivity
import com.rantolin.marvelcomics.presentation.ui.activities.MainActivity
import com.rantolin.marvelcomics.presentation.ui.adapters.ComicListViewAdapter
import org.junit.Test

/**
 * Created by ricar on 9/4/17.
 */

class MainActivityTest: AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun clickOnFirstItemTest(){
        ConditionWatcher.waitForCondition(LoadingConditionMatcher())
        onView(withId(R.id.itemsList))
                .perform(RecyclerViewActions.
                        actionOnItemAtPosition<ComicListViewAdapter.HomeViewHolder>(1, click()))

        Matchers.nextOpenActivityIs(DetailsActivity::class.java)
    }
}