package com.app.torch.utils.recyclerview.alphabet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.recyclerview.widget.RecyclerView;

import com.app.torch.R;

public class AlphabetScrollRecyclerViewItemDecoration extends RecyclerView.ItemDecoration{
    public AlphabetScrollRecyclerViewItemDecoration() {
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);

        float scaledWidth = ((AlphabetScrollRecyclerView)parent).scaledWidth;
        float sx = ((AlphabetScrollRecyclerView)parent).sx;
        float scaledHeight= ((AlphabetScrollRecyclerView)parent).scaledHeight;
        float sy = ((AlphabetScrollRecyclerView)parent).sy;
        String[] sections = ((AlphabetScrollRecyclerView)parent).sections;
        String section = ((AlphabetScrollRecyclerView)parent).section;
        boolean showLetter = ((AlphabetScrollRecyclerView)parent).showLetter;

        // We draw the letter in the middle
        if (showLetter & section != null && !section.equals("")) {
            //overlay everything when displaying selected index Letter in the middle
            Paint overlayDark = new Paint();
            overlayDark.setColor(Color.BLACK);
            overlayDark.setAlpha(100);
            canvas.drawRect(0, 0, parent.getWidth(), parent.getHeight(), overlayDark);
            float middleTextSize = parent.getResources().getDimension(R.dimen.dimen_alphabet_overlay_text_size);
            Paint middleLetter = new Paint();
            middleLetter.setColor(Color.WHITE);
            middleLetter.setTextSize(middleTextSize);
            middleLetter.setAntiAlias(true);
            middleLetter.setFakeBoldText(true);
            middleLetter.setStyle(Paint.Style.FILL);
            int xPos = (canvas.getWidth() -  (int)middleTextSize)/ 2;
            int yPos = (int) ((canvas.getHeight() / 2) - ((middleLetter.descent() + middleLetter.ascent()) / 2));

            canvas.drawText(section.toUpperCase(), xPos, yPos, middleLetter);
        }

        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < sections.length; i++) {
            if(showLetter & section != null && !section.equals("") && section!=null
                    && sections[i].toUpperCase().equals(section.toUpperCase())) {
                textPaint.setColor(Color.WHITE);
                textPaint.setAlpha(255);
                textPaint.setFakeBoldText(true);
                textPaint.setTextSize((float)(scaledWidth / 2));
                canvas.drawText(sections[i].toUpperCase(),
                        sx + textPaint.getTextSize() / 2, sy + parent.getPaddingTop()
                                + scaledHeight * (i + 1), textPaint);
                textPaint.setTextSize((float)(scaledWidth));
                canvas.drawText("•",
                        sx - textPaint.getTextSize()/3, sy+parent.getPaddingTop()
                                + scaledHeight * (i + 1) + scaledHeight/3, textPaint);

            } else {
                textPaint.setColor(Color.LTGRAY);
                textPaint.setAlpha(200);
                textPaint.setFakeBoldText(false);
                textPaint.setTextSize(scaledWidth / 2);
                canvas.drawText(sections[i].toUpperCase(),
                        sx + textPaint.getTextSize() / 2, sy + parent.getPaddingTop()
                                + scaledHeight * (i + 1), textPaint);
            }
        }
    }
}
