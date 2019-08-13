package com.aurelhubert.ahbottomnavigation;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

public class AHTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Integer originalTextColor;

    public AHTextView(Context context) {
        super(context);
    }

    public AHTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AHTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextColor(@Nullable Integer color) {
        saveOriginalTextColor();
        if (color == null) {
            super.setTextColor(originalTextColor);
        } else {
            super.setTextColor(color);
        }
    }

    private void saveOriginalTextColor() {
        if (originalTextColor == null) {
            originalTextColor = getCurrentTextColor();
        }
    }
}
