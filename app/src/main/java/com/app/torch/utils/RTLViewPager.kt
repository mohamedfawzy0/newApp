package com.app.torch.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

open class RtlViewPager : ViewPager {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onRtlPropertiesChanged(layoutDirection: Int) {
        super.onRtlPropertiesChanged(layoutDirection)
        if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            rotationY = 180f
        }
    }

    override fun onViewAdded(child: View?) {
        if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            child?.rotationY = 180f
        }
        super.onViewAdded(child)
    }

}