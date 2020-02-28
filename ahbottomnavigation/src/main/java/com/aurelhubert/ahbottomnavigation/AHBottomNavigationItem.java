package com.aurelhubert.ahbottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

/**
 * AHBottomNavigationItem
 * The item is display in the AHBottomNavigation layout
 */
public class AHBottomNavigationItem {
	
	private String title = "";
	private Drawable icon;
    private Drawable selectedIcon;
    private String tag;
    private int color = Color.GRAY;

    private @DrawableRes int iconRes = 0;
    private @DrawableRes int selectedIconRes = 0;
	private @StringRes int titleRes = 0;
	private @ColorRes int colorRes = 0;

	/**
	 * Constructor
	 *
	 * @param title    Title
	 * @param iconRes Drawable resource
	 */
	public AHBottomNavigationItem(String title, @DrawableRes int iconRes) {
		this.title = title;
		this.iconRes = iconRes;
	}

	/**
	 * @param title    Title
	 * @param iconRes Drawable resource
	 * @param color    Background color
	 */
	@Deprecated
	public AHBottomNavigationItem(String title, @DrawableRes int iconRes, @ColorRes int color) {
		this.title = title;
		this.iconRes = iconRes;
		this.color = color;
	}
	
	/**
	 * Constructor
	 *
	 * @param titleRes    String resource
	 * @param iconRes Drawable resource
	 * @param colorRes    Color resource
	 */
	public AHBottomNavigationItem(@StringRes int titleRes, @DrawableRes int iconRes, @ColorRes int colorRes) {
		this.titleRes = titleRes;
		this.iconRes = iconRes;
		this.colorRes = colorRes;
	}

    /**
     * Constructor
     *
     * @param titleRes    String resource
     * @param iconRes Drawable resource
     */
    public AHBottomNavigationItem(@StringRes int titleRes, @DrawableRes int iconRes) {
        this.titleRes = titleRes;
        this.iconRes = iconRes;
    }

    /**
	 * Constructor
	 *
	 * @param title    String
	 * @param drawable Drawable
	 */
	public AHBottomNavigationItem(String title, Drawable drawable) {
		this.title = title;
		this.icon = drawable;
	}

    /**
     * Constructor
     *
     * @param title    String
     * @param icon Drawable
     * @param tag String
     */
    public AHBottomNavigationItem(String title, Drawable icon, Drawable selectedIcon, String tag) {
        this.title = title;
        this.icon = icon;
        this.selectedIcon = selectedIcon;
        this.tag = tag;
    }

	/**
	 * Constructor
	 *
	 * @param title    String
	 * @param drawable Drawable
	 * @param color    Color
	 */
	public AHBottomNavigationItem(String title, Drawable drawable, @ColorInt int color) {
		this.title = title;
		this.icon = drawable;
		this.color = color;
	}

    public String getTitle(Context context) {
		if (titleRes != 0) {
			return context.getString(titleRes);
		}
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		this.titleRes = 0;
	}
	
	public void setTitle(@StringRes int titleRes) {
		this.titleRes = titleRes;
		this.title = "";
	}
	
	public int getColor(Context context) {
		if (colorRes != 0) {
			return ContextCompat.getColor(context, colorRes);
		}
		return color;
	}
	
	public void setColor(@ColorInt int color) {
		this.color = color;
		this.colorRes = 0;
	}
	
	public void setColorRes(@ColorRes int colorRes) {
		this.colorRes = colorRes;
		this.color = 0;
	}
	
	public Drawable getDrawable(Context context) {
	    Drawable icon = this.icon == null ? getResourceDrawable(context, iconRes) : this.icon;
	    Drawable selectedIcon = this.selectedIcon == null ? getResourceDrawable(context, selectedIconRes) : this.selectedIcon;

        StateListDrawable stateDrawable = new StateListDrawable();
        if (selectedIcon != null) stateDrawable.addState(new int[] { android.R.attr.state_selected }, selectedIcon);
        stateDrawable.addState(new int[] {}, icon);
        return stateDrawable;
	}

	public void setIcon(@DrawableRes int drawableRes) {
		this.iconRes = drawableRes;
		this.icon = null;
	}
	
	public void setIcon(Drawable drawable) {
		this.icon = drawable;
		this.iconRes = 0;
	}

    public void setSelectedIcon(Drawable selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public void setSelectedIcon(@DrawableRes int selectedIconRes) {
        this.selectedIconRes = selectedIconRes;
    }

    public String getTag() {
        return tag;
    }

    private Drawable getResourceDrawable(Context context, @DrawableRes int drawableRes) {
        if (drawableRes != 0) {
            try {
                return AppCompatResources.getDrawable(context, drawableRes);
            } catch (Resources.NotFoundException e) {
                return ContextCompat.getDrawable(context, drawableRes);
            }
        }
        return null;
    }

    public boolean hasIcon() {
	    return icon != null ||
               iconRes != 0 ||
               selectedIcon != null ||
               selectedIconRes != 0;
    }
}
