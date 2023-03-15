package com.app.torch.utils.extensions.textview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView


@SuppressLint("AppCompatCustomView")
class CustomTextView : TextView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}