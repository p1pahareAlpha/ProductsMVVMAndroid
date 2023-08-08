package com.example.myapplication.di.network

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions


@GlideModule
class AppGlideModule : AppGlideModule()

fun ImageView.load(imageAddress: String, requestOptions: RequestOptions) {
    Glide.with(this)
        .load(imageAddress)
        .apply(requestOptions)
        .skipMemoryCache(true)//for caching the image url in case phone is offline
        .into(this  )
}
