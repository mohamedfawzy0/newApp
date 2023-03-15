package com.app.torch.utils.recyclerview.alphabet;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class AlphabetScrollRecyclerView extends RecyclerView {
    private Context ctx;

    private boolean setupThings = false;
    public static int indWidth = 25;
    public static int indHeight = 18;
    public float scaledWidth;
    public float scaledHeight;
    public String[] sections;
    public float sx;
    public float sy;
    public String section;
    public boolean showLetter = false;
    private Handler listHandler;
    private RecyclerView.SmoothScroller smoothScroller;

    public AlphabetScrollRecyclerView(Context context) {
        super(context);
        ctx = context;
    }

    public AlphabetScrollRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
    }

    public AlphabetScrollRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx = context;
    }

    @Override
    public void onDraw(Canvas c) {
        if (!setupThings)
            setupThings();
        super.onDraw(c);
    }

    private void setupThings() {
        //create az text data
        Set<String> sectionSet = ((AlphabetScrollRecyclerViewInterface) getAdapter()).getMapIndex().keySet();
        ArrayList<String> listSection = new ArrayList<>(sectionSet);
        Collections.sort(listSection);
        sections = new String[listSection.size()];
        int i = 0;
        for (String s : listSection) {
            sections[i++] = s;
        }

        smoothScroller = new LinearSmoothScroller(ctx) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };

        scaledWidth = indWidth * ctx.getResources().getDisplayMetrics().density;
        scaledHeight = indHeight * ctx.getResources().getDisplayMetrics().density;
        sx = this.getWidth() - this.getPaddingRight() - (float) (1.2 * scaledWidth);
        sy = (float) ((this.getHeight() - (scaledHeight * sections.length)) / 2.0);
        setupThings = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (x < sx - scaledWidth || y < sy || y > (sy + scaledHeight * sections.length))
                    return super.onTouchEvent(event);
                else {
                    // We touched the index bar
                    float yy = y - this.getPaddingTop() - getPaddingBottom() - sy;
                    int currentPosition = (int) Math.floor(yy / scaledHeight);
                    if (currentPosition < 0) currentPosition = 0;
                    if (currentPosition >= sections.length) currentPosition = sections.length - 1;
                    section = sections[currentPosition];
                    showLetter = true;
                    int position = 0;
                    if (((AlphabetScrollRecyclerViewInterface) getAdapter()).getMapIndex().containsKey(section.toUpperCase()))
                        position = ((AlphabetScrollRecyclerViewInterface) getAdapter()).getMapIndex().get(section.toUpperCase());
                    this.scroll(position);
                    AlphabetScrollRecyclerView.this.invalidate();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!showLetter && (x < sx - scaledWidth || y < sy || y > (sy + scaledHeight * sections.length)))
                    return super.onTouchEvent(event);
                else {
                    float yy = y - sy;
                    int currentPosition = (int) Math.floor(yy / scaledHeight);
                    if (currentPosition < 0) currentPosition = 0;
                    if (currentPosition >= sections.length) currentPosition = sections.length - 1;
                    section = sections[currentPosition];
                    showLetter = true;
                    int position = 0;
                    if (((AlphabetScrollRecyclerViewInterface) getAdapter()).getMapIndex().containsKey(section.toUpperCase()))
                        position = ((AlphabetScrollRecyclerViewInterface) getAdapter()).getMapIndex().get(section.toUpperCase());
                    this.scroll(position);
                    AlphabetScrollRecyclerView.this.invalidate();
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                listHandler = new ListHandler();
                listHandler.sendEmptyMessageDelayed(0, 100);
                if (x < sx - scaledWidth || y < sy || y > (sy + scaledHeight * sections.length))
                    return super.onTouchEvent(event);
                else
                    return true;
            }
        }
        return true;
    }

    private void scroll(int position) {
        smoothScroller.setTargetPosition(position);
        getLayoutManager().startSmoothScroll(smoothScroller);
    }

    private class ListHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showLetter = false;
            AlphabetScrollRecyclerView.this.invalidate();
        }
    }
}
