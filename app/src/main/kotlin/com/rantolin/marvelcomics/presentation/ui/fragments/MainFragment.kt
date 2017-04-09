package com.rantolin.marvelcomics.presentation.ui.fragments

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.rantolin.marvelcomics.domain.model.ComicModel
import com.rantolin.marvelcomics.presentation.AndroidApplication
import com.rantolin.marvelcomics.presentation.R
import com.rantolin.marvelcomics.presentation.internal.di.components.DaggerFragmentComponent
import com.rantolin.marvelcomics.presentation.internal.di.components.FragmentComponent
import com.rantolin.marvelcomics.presentation.internal.di.modules.FragmentModule
import com.rantolin.marvelcomics.presentation.ui.activities.COMIC_EXTRAS
import com.rantolin.marvelcomics.presentation.ui.activities.MainActivity
import com.rantolin.marvelcomics.presentation.ui.adapters.ComicListViewAdapter
import com.rantolin.marvelcomics.presentation.ui.presenters.MainPresenter
import com.rantolin.marvelcomics.presentation.ui.views.MainView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.progress_layout.*

import java.util.*
import javax.inject.Inject

class MainFragment : BaseFragment(), MainView {
    private val spanCount = 2
    private val orientation = StaggeredGridLayoutManager.VERTICAL
    private var comicList: List<ComicModel> = ArrayList()
    private var adapter = ComicListViewAdapter(comicList) {
        goToUserDetails(it)
    }

    @Inject
    lateinit var presenter: MainPresenter


    private val component: FragmentComponent
        get() = DaggerFragmentComponent.builder()
                .applicationComponent((activity.application as AndroidApplication).component)
                .fragmentModule(FragmentModule(this))
                .build()


    companion object {
        fun newInstance(bundle: Bundle?): MainFragment {
            val fragment = MainFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(context, "Something was wrong: ${throwable.message}", Toast.LENGTH_LONG).show()
        Log.d("Error", throwable.message)
    }

    override fun searchResult(comicList: List<ComicModel>) {
        adapter.setComicList(comicList)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_main, container, false)
        component.inject(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate(this)

        val staggeredLM = StaggeredGridLayoutManager(spanCount, orientation)
        staggeredLM.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE

        itemsList.adapter = adapter
        itemsList.setHasFixedSize(true)
        itemsList.isDrawingCacheEnabled = true
        itemsList.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH;
        itemsList.layoutManager = staggeredLM
        itemsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                (recyclerView.layoutManager as StaggeredGridLayoutManager).invalidateSpanAssignments()
            }
        })


    }

    fun goToUserDetails(comicModel: ComicModel) {
        val extras = Bundle()
        extras.putParcelable(COMIC_EXTRAS, comicModel)
        (activity as MainActivity).navigateToDetailsActivity(extras)
    }

    override fun showLoading() {
        progressLayout.visibility = VISIBLE
    }

    override fun hideLoading() {
        progressLayout.visibility = GONE
    }
}