package com.aurelhubert.ahbottomnavigation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 */
@SuppressWarnings({"WeakerAccess", "MagicNumber"})
public class AHHelper {

	/**
	 * Return a tint drawable
	 */
	public static Drawable getTintDrawable(Drawable drawable, @Nullable Integer color, boolean forceTint) {
        if (color == null) {
            Drawable wrapDrawable = DrawableCompat.wrap(drawable).mutate();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                wrapDrawable.setTintList(null);
            }
            return wrapDrawable;
        }
		if (forceTint) {
			drawable.clearColorFilter();
			drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
			drawable.invalidateSelf();
			return drawable;
		}
		Drawable wrapDrawable = DrawableCompat.wrap(drawable).mutate();
		DrawableCompat.setTint(wrapDrawable, color);
		return wrapDrawable;
	}

	/**
	 * Update top margin with animation
	 */
	public static void updateTopMargin(final View view, int fromMargin, int toMargin) {
		ValueAnimator animator = ValueAnimator.ofFloat(fromMargin, toMargin);
		animator.setDuration(150);
		animator.addUpdateListener(valueAnimator -> {
            float animatedValue = (float) valueAnimator.getAnimatedValue();
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                p.setMargins(p.leftMargin, (int) animatedValue, p.rightMargin, p.bottomMargin);
                view.requestLayout();
            }
        });
		animator.start();
	}

	/**
	 * Update bottom margin with animation
	 */
	public static void updateBottomMargin(final View view, int fromMargin, int toMargin, int duration) {
		ValueAnimator animator = ValueAnimator.ofFloat(fromMargin, toMargin);
		animator.setDuration(duration);
		animator.addUpdateListener(valueAnimator -> {
            float animatedValue = (float) valueAnimator.getAnimatedValue();
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                p.setMargins(p.leftMargin, p.topMargin, p.rightMargin, (int) animatedValue);
                view.requestLayout();
            }
        });
		animator.start();
	}

	/**
	 * Update left margin with animation
	 */
	public static void updateLeftMargin(final View view, int fromMargin, int toMargin) {
		ValueAnimator animator = ValueAnimator.ofFloat(fromMargin, toMargin);
		animator.setDuration(150);
		animator.addUpdateListener(valueAnimator -> {
            float animatedValue = (float) valueAnimator.getAnimatedValue();
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                p.setMargins((int) animatedValue, p.topMargin, p.rightMargin, p.bottomMargin);
                view.requestLayout();
            }
        });
		animator.start();
	}

	/**
	 * Update text size with animation
	 */
	public static void updateTextSize(final TextView textView, float fromSize, float toSize) {
		ValueAnimator animator = ValueAnimator.ofFloat(fromSize, toSize);
		animator.setDuration(150);
		animator.addUpdateListener(valueAnimator -> {
            float animatedValue = (float) valueAnimator.getAnimatedValue();
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, animatedValue);
        });
		animator.start();
	}

	/**
	 * Update alpha
	 */
	public static void updateAlpha(final View view, float fromValue, float toValue) {
		ValueAnimator animator = ValueAnimator.ofFloat(fromValue, toValue);
		animator.setDuration(150);
		animator.addUpdateListener(valueAnimator -> {
            float animatedValue = (float) valueAnimator.getAnimatedValue();
            view.setAlpha(animatedValue);
        });
		animator.start();
	}

	/**
	 * Update text color with animation
	 */
	public static void updateTextColor(final AHTextView textView, @Nullable Integer fromColor, @Nullable Integer toColor) {
        if (fromColor != null && toColor != null) {
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
            colorAnimation.setDuration(150);
            colorAnimation.addUpdateListener(animator -> textView.setTextColor((Integer) animator.getAnimatedValue()));
            colorAnimation.start();
        } else {
            textView.setTextColor(toColor);
        }
	}

	/**
	 * Update text color with animation
	 */
	public static void updateViewBackgroundColor(final View view, @ColorInt int fromColor, @ColorInt int toColor) {
		ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
		colorAnimation.setDuration(150);
		colorAnimation.addUpdateListener(animator -> view.setBackgroundColor((Integer) animator.getAnimatedValue()));
		colorAnimation.start();
	}

	/**
	 * Update image view color with animation
	 */
	public static void updateDrawableColor(final Drawable drawable,
                                           final ImageView imageView, @Nullable Integer fromColor,
                                           @Nullable Integer toColor, final boolean forceTint) {
        if (fromColor != null && toColor != null) {
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
            colorAnimation.setDuration(150);
            colorAnimation.addUpdateListener(animator -> {
                imageView.setImageDrawable(AHHelper.getTintDrawable(drawable, (Integer) animator.getAnimatedValue(), forceTint));
                imageView.requestLayout();
            });
            colorAnimation.start();
        } else {
            imageView.setImageDrawable(AHHelper.getTintDrawable(drawable, toColor, forceTint));
            imageView.requestLayout();
        }
	}

	/**
	 * Update width
	 */
	public static void updateWidth(final View view, float fromWidth, float toWidth) {
		ValueAnimator animator = ValueAnimator.ofFloat(fromWidth, toWidth);
		animator.setDuration(150);
		animator.addUpdateListener(animator1 -> {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.width = Math.round((float) animator1.getAnimatedValue());
            view.setLayoutParams(params);
        });
		animator.start();
	}

	/**
	 * Check if the status bar is translucent
	 *
	 * @param context Context
	 */
	public static boolean isTranslucentStatusBar(Context context) {
		Window w = unwrap(context).getWindow();
		WindowManager.LayoutParams lp = w.getAttributes();
		int flags = lp.flags;
		if ((flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION) == WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION) {
			return true;
		}

		return false;
	}

	/**
	 * Get the height of the buttons bar
	 *
	 * @param context Context
	 */
	public static int getSoftButtonsBarSizePort(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			DisplayMetrics metrics = new DisplayMetrics();
			Window window = unwrap(context).getWindow();
			window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int usableHeight = metrics.heightPixels;
			window.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
			int realHeight = metrics.heightPixels;
			if (realHeight > usableHeight)
				return realHeight - usableHeight;
			else
				return 0;
		}
		return 0;
	}

	/**
	 * Unwrap activity
	 *
	 * @param context Context
	 * @return Activity
	 */
	public static Activity unwrap(Context context) {
		while (!(context instanceof Activity)) {
			ContextWrapper wrapper = (ContextWrapper) context;
			context = wrapper.getBaseContext();
		}
		return (Activity) context;
	}

	public interface Mapper<T> {
	    T map(T value);
    }

    public static <T> void map(ArrayList<T> al, Mapper<T> mapper) {
	    if (al == null) return;
        for (int i = 0; i < al.size(); i++) {
            al.set(i, mapper.map(al.get(i)));
        }
    }

    public static <T> void fill(ArrayList<T> al, int count, T value) {
        for (int i = 0; i < count; i++) {
            al.add(value);
        }
    }

    public static boolean equals(@Nullable Object o1, @Nullable Object o2) {
        return o1 == null && o2 == null || o1 != null && o2 != null && o1.equals(o2);
    }
}
