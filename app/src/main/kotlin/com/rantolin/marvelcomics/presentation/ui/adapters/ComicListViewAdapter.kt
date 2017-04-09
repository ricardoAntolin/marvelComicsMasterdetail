package com.rantolin.marvelcomics.presentation.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.rantolin.marvelcomics.domain.model.ComicModel
import com.rantolin.marvelcomics.presentation.R
import com.rantolin.marvelcomics.utils.inflate
import com.rantolin.marvelcomics.utils.loadUrl
import kotlinx.android.synthetic.main.item_home.view.*

class ComicListViewAdapter(var objects: List<ComicModel>, val listener: (ComicModel) -> Unit) :
        RecyclerView.Adapter<ComicListViewAdapter.HomeViewHolder>() {
    override fun getItemCount(): Int = objects.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            HomeViewHolder = HomeViewHolder(parent.inflate(R.layout.item_home))

    override fun onBindViewHolder(vh: HomeViewHolder, i: Int) = vh.bind(objects[i], listener)

    class HomeViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(comicModel: ComicModel, listener: (ComicModel) -> Unit) = with(itemview) {
            cardImage.layout(0, 0, 0, 0)
            cardImage.loadUrl(comicModel.thumbnail)
            cardTitle.text = comicModel.title
            setOnClickListener { listener(comicModel) }
        }
    }

    fun setComicList(comicList: List<ComicModel>){
        objects = comicList
        notifyDataSetChanged()
    }
}