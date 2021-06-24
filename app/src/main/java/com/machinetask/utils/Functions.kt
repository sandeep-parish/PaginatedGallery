package com.machinetask.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.machinetask.R

fun getImageLoaderProgress(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}

private fun Context.getGlideRequestOptions(): RequestOptions {
    val requestOptions = RequestOptions()
    requestOptions.error(R.drawable.image_placeholder)
    requestOptions.placeholder(getImageLoaderProgress(this))
    return requestOptions
}

fun ImageView.loadImage(
    url: String? = "",
    requestOptions: RequestOptions = context.getGlideRequestOptions(),
    sizeMultiplier: Float = 0.5f
) {
    Glide.with(context)
        .load(url)
        .thumbnail(sizeMultiplier)
        .apply(requestOptions)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this);
}