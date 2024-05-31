package com.aurelhubert.ahbottomnavigation.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;

/**
 * @author repitch
 */
public class AHNotification implements Parcelable {
    public static final int NOTIFICATION_SIZE_DEFAULT = -1;

    @Nullable
    private String text; // can be null, so notification will not be shown

    @ColorInt
    private int textColor; // if 0 then use default value

    @ColorInt
    private int backgroundColor; // if 0 then use default value

    @Px
    private int size = NOTIFICATION_SIZE_DEFAULT;

    private boolean animate = false;

    public AHNotification() {
        // empty
    }

    private AHNotification(Parcel in) {
        text = in.readString();
        textColor = in.readInt();
        backgroundColor = in.readInt();
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(text) && size <= 0;
    }

    public boolean hasValue() {
        return text != null || size != NOTIFICATION_SIZE_DEFAULT;
    }

    public boolean isDot() {
        return TextUtils.isEmpty(text) && size >= 0;
    }

    @NonNull
    public String getReadableText() {
        return text == null ? "" : text;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(@Px int size) {
        this.size = size;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public boolean shouldAnimate() {
        return animate;
    }

    public static AHNotification justText(String text) {
        return new Builder().setText(text).build();
    }

    public static List<AHNotification> generateEmptyList(int size) {
        List<AHNotification> notificationList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            notificationList.add(new AHNotification());
        }
        return notificationList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeInt(textColor);
        dest.writeInt(backgroundColor);
        dest.writeInt(size);
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(text);
    }

    public static class Builder {
        @Nullable
        private String text;
        @ColorInt
        private int textColor;
        @ColorInt
        private int backgroundColor;
        @Px
        private int size = NOTIFICATION_SIZE_DEFAULT;
        private boolean animate = false;

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setTextColor(@ColorInt Integer textColor) {
            if (textColor == null) return this;
            this.textColor = textColor;
            return this;
        }

        public Builder setBackgroundColor(@ColorInt Integer backgroundColor) {
            if (backgroundColor == null) return this;
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setSize(@Px int size) {
            this.size = size;
            return this;
        }

        public Builder animate(boolean animate) {
            this.animate = animate;
            return this;
        }

        public AHNotification build() {
            AHNotification notification = new AHNotification();
            notification.text = text;
            notification.textColor = textColor;
            notification.backgroundColor = backgroundColor;
            notification.size = size;
            notification.animate = animate;
            return notification;
        }
    }

    public static final Creator<AHNotification> CREATOR = new Creator<AHNotification>() {
        @Override
        public AHNotification createFromParcel(Parcel in) {
            return new AHNotification(in);
        }

        @Override
        public AHNotification[] newArray(int size) {
            return new AHNotification[size];
        }
    };

}
