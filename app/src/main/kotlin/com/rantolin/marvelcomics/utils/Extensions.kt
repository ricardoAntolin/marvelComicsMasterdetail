package com.rantolin.marvelcomics.utils

import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget


fun ImageView.loadUrl(url:String?){
    Glide.with(context)
            .load(url)
            .into(this)
}

fun FloatingActionButton.loadUrlAsBackgroud(url:String){
    Glide.with(context)
            .load(url)
            .into(object : SimpleTarget<GlideDrawable>() {
                override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>?) {
                    this@loadUrlAsBackgroud.background = resource
                }
            })
}


fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}