package com.rantolin.marvelcomics

import android.support.test.InstrumentationRegistry
import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.azimolabs.conditionwatcher.Instruction
import com.rantolin.marvelcomics.presentation.AndroidApplication
import com.rantolin.marvelcomics.presentation.R
import com.rantolin.marvelcomics.presentation.ui.activities.MainActivity
import com.rantolin.marvelcomics.presentation.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.progress_layout.*


/**
 * Created by ricar on 9/4/17.
 */
class LoadingConditionMatcher: Instruction() {
    override fun getDescription(): String {
        return "Loading dialog shouldn't be in view hierarchy"
    }

    override fun checkCondition(): Boolean {
        val activity = (InstrumentationRegistry.getTargetContext()?.applicationContext as AndroidApplication).getCurrentActivity() ?: return false
        val f = (activity as MainActivity).fragment
        val progressLayout = f.view?.findViewById(R.id.progressLayout) ?: return false
        return progressLayout.visibility == View.GONE
    }
}
