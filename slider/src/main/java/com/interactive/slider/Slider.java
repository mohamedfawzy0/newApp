package com.interactive.slider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import java.util.List;
import java.util.Random;

import com.interactive.slider.ui.transformers.TransformersGroup;
import com.interactive.slider.ui.adapter.SliderAdapter;
import com.interactive.slider.ui.custom.LooperWrapViewPager;
import com.interactive.slider.ui.indicators.IndicatorShape;
import com.interactive.slider.model.Slide;
import com.interactive.slider.ui.indicators.SlideIndicatorsGroup;

import com.interactive.slider.ui.R;

public class Slider extends FrameLayout implements ViewPager.OnPageChangeListener {

    public static final String TAG = "SLIDER";

    private LooperWrapViewPager viewPager;
    private AdapterView.OnItemClickListener itemClickListener;

    //Custom attributes
    private Drawable selectedSlideIndicator;
    private Drawable unSelectedSlideIndicator;
    private Drawable sliderImageDefault;
    private int defaultIndicator;
    private int indicatorSize;
    private int defaultTransformer;
    private boolean mustAnimateIndicators;
    private boolean mustLoopSlides;
    private SlideIndicatorsGroup slideIndicatorsGroup;
    private int slideShowInterval = 5000;

    private Handler handler = new Handler();
    private int slideCount;
    private int currentPageNumber;
    private int pageMargin;
    private int paddingTop;
    private int paddingLeft;
    private int paddingRight;
    private int paddingBottom;
    private boolean hideIndicators = false;

    public Slider(@NonNull Context context) {
        super(context);
    }

    public Slider(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseCustomAttributes(attrs);
    }

    public Slider(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseCustomAttributes(attrs);
    }

    private void parseCustomAttributes(AttributeSet attributeSet) {
        try {
            if (attributeSet != null) {
                TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.BannerSlider);
                try {
                    indicatorSize = typedArray.getDimensionPixelSize(R.styleable.BannerSlider_indicatorSize, getResources().getDimensionPixelSize(R.dimen.default_indicator_size));
                    pageMargin = typedArray.getDimensionPixelSize(R.styleable.BannerSlider_pageMargin, getResources().getDimensionPixelSize(R.dimen.default_page_margin));
                    paddingTop = typedArray.getDimensionPixelSize(R.styleable.BannerSlider_paddingTop, getResources().getDimensionPixelSize(R.dimen.default_padding));
                    paddingLeft = typedArray.getDimensionPixelSize(R.styleable.BannerSlider_paddingLeft, getResources().getDimensionPixelSize(R.dimen.default_padding));
                    paddingRight = typedArray.getDimensionPixelSize(R.styleable.BannerSlider_paddingRight, getResources().getDimensionPixelSize(R.dimen.default_padding));
                    paddingBottom = typedArray.getDimensionPixelSize(R.styleable.BannerSlider_paddingBottom, getResources().getDimensionPixelSize(R.dimen.default_padding));
                    selectedSlideIndicator = typedArray.getDrawable(R.styleable.BannerSlider_selected_slideIndicator);
                    unSelectedSlideIndicator = typedArray.getDrawable(R.styleable.BannerSlider_unselected_slideIndicator);
                    sliderImageDefault = typedArray.getDrawable(R.styleable.BannerSlider_imageDefault);
                    defaultIndicator = typedArray.getInt(R.styleable.BannerSlider_defaultIndicators, IndicatorShape.CIRCLE);
                    mustAnimateIndicators = typedArray.getBoolean(R.styleable.BannerSlider_animateIndicators, true);
                    mustLoopSlides = typedArray.getBoolean(R.styleable.BannerSlider_loopSlides, false);
                    hideIndicators = typedArray.getBoolean(R.styleable.BannerSlider_hideIndicators, false);
                    int slideShowIntervalSecond = typedArray.getInt(R.styleable.BannerSlider_intervalSecond, 5);
                    defaultTransformer= typedArray.getInt(R.styleable.BannerSlider_defaultTransformer, 9);
                    slideShowInterval = slideShowIntervalSecond * 1000;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    typedArray.recycle();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSlides(List<Slide> slideList){
        addSlides(slideList,null,null);
    }

    public void addSlides(List<Slide> slideList, Drawable imageDefault){
        addSlides(slideList,imageDefault,null);
    }

    public void addSlides(List<Slide> slideList, ViewPager.PageTransformer transformer){
        addSlides(slideList,null,transformer);
    }

    public void addSlides(List<Slide> slideList, Drawable imageDefault, ViewPager.PageTransformer transformer) {
        if (slideList == null || slideList.size() == 0)
            return;

        if (imageDefault!=null)
            sliderImageDefault=imageDefault;

        viewPager = new LooperWrapViewPager(getContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            viewPager.setId(View.generateViewId());
        } else {
            int id = Math.abs(new Random().nextInt((5000 - 1000) + 1) + 1000);
            viewPager.setId(id);
        }

        if (transformer!=null)
            viewPager.setPageTransformer(false,transformer);
        else
            viewPager.setPageTransformer(false,new TransformersGroup().getTransformer(getContext(),defaultTransformer));

        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        viewPager.addOnPageChangeListener(Slider.this);
        viewPager.setClipChildren(false);
        viewPager.setClipToPadding(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(pageMargin);
        viewPager.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
        addView(viewPager);
        SliderAdapter adapter = new SliderAdapter(getContext(), slideList, sliderImageDefault,new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(adapterView, view, i, l);
            }
        });
        viewPager.setAdapter(adapter);
        slideCount = slideList.size();
        viewPager.setCurrentItem(slideCount - 1);
        if (!hideIndicators && slideCount > 1) {
            slideIndicatorsGroup = new SlideIndicatorsGroup(getContext(), selectedSlideIndicator, unSelectedSlideIndicator, defaultIndicator, indicatorSize, mustAnimateIndicators);
            addView(slideIndicatorsGroup);
            slideIndicatorsGroup.setSlides(slideCount);
            slideIndicatorsGroup.onSlideChange(slideCount - 1);
        }
        if (slideCount > 1)
            setupTimer();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPageNumber = position;
        if (slideIndicatorsGroup != null && !hideIndicators) {
            if (position == 0) {
                slideIndicatorsGroup.onSlideChange(slideCount - 1);
            } else if (position == slideCount + 1) {
                slideIndicatorsGroup.onSlideChange(0);
            } else {
                slideIndicatorsGroup.onSlideChange(position - 1);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                handler.removeCallbacksAndMessages(null);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                setupTimer();
                break;
        }
    }

    private void setupTimer() {
        try {
            if (mustLoopSlides) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (currentPageNumber < slideCount)
                                currentPageNumber += 1;
                            else
                                currentPageNumber = 1;

                            viewPager.setCurrentItem(currentPageNumber - 1, true);

                            handler.removeCallbacksAndMessages(null);
                            handler.postDelayed(this, slideShowInterval);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, slideShowInterval);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // setters
    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setHideIndicators(boolean hideIndicators) {
        this.hideIndicators = hideIndicators;
        try {
            if (hideIndicators)
                slideIndicatorsGroup.setVisibility(INVISIBLE);
            else
                slideIndicatorsGroup.setVisibility(VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
