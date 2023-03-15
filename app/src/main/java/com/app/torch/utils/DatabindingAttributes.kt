package com.app.torch.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.torch.utils.extensions.imageview.load
import de.hdodenhof.circleimageview.CircleImageView

class DatabindingAttributes {
    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageView(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl)
        }

        @JvmStatic
        @BindingAdapter("android:src")
        fun setCircleImageView(imageView: CircleImageView, imageUrl: String) {
            imageView.load(imageUrl)
        }
    }
}