package com.app.torch.utils.extensions.imageview

import android.net.Uri
import android.widget.ImageView
import com.app.torch.R
import com.bumptech.glide.Glide

enum class ImageViewMode {
    NORMAL, CENTER_CROP, FIT_CENTER
}

fun ImageView.load(url: String, mode: ImageViewMode = ImageViewMode.NORMAL) {
    val load = Glide.with(context).load(url).error(R.drawable.ic_image)
    when (mode) {
        ImageViewMode.NORMAL -> load.into(this)
        ImageViewMode.CENTER_CROP -> load.centerCrop().into(this)
        ImageViewMode.FIT_CENTER -> load.fitCenter().into(this)
    }
}

fun ImageView.load(uri: Uri, mode: ImageViewMode = ImageViewMode.NORMAL) {
    val load = Glide.with(context).load(uri).error(R.drawable.ic_image)
    when (mode) {
        ImageViewMode.NORMAL -> load.into(this)
        ImageViewMode.CENTER_CROP -> load.centerCrop().into(this)
        ImageViewMode.FIT_CENTER -> load.fitCenter().into(this)
    }
}

fun ImageView.load(resourceId: Int, mode: ImageViewMode = ImageViewMode.NORMAL) {
    val load = Glide.with(context).load(resourceId).error(R.drawable.ic_image)
    when (mode) {
        ImageViewMode.NORMAL -> load.into(this)
        ImageViewMode.CENTER_CROP -> load.centerCrop().into(this)
        ImageViewMode.FIT_CENTER -> load.fitCenter().into(this)
    }
}

fun ImageView.load(url: String, defaultResourceId: Int, mode: ImageViewMode = ImageViewMode.NORMAL) {
    val load = Glide.with(context).load(url).placeholder(defaultResourceId).error(defaultResourceId)
    when (mode) {
        ImageViewMode.NORMAL -> load.into(this)
        ImageViewMode.CENTER_CROP -> load.centerCrop().into(this)
        ImageViewMode.FIT_CENTER -> load.fitCenter().into(this)
    }
}