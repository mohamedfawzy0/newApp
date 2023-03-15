package com.interactive.slider.ui.transformers;

import androidx.viewpager.widget.ViewPager;

import android.view.View;

public class FadeOutTransformer implements ViewPager.PageTransformer {

    public void transformPage(View page, float position) {
        page.setTranslationX(-position * page.getWidth());
        page.setAlpha(1 - Math.abs(position));
    }
}
