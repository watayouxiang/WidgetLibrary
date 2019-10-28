package com.watayouxiang.widgetlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StaticItem extends RelativeLayout {
    public TextView tvRedPoint;
    public TextView tvTitle;
    public TextView tvInfo;
    public ImageView ivArrow;
    public View vDivider;

    private boolean mShowPoint;
    private boolean mShowArrow;
    private boolean mShowDivider;
    private String mTitleText;

    public StaticItem(Context context) {
        this(context, null);
    }

    public StaticItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StaticItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.static_item, this, true);
        findView();
        obtainAttributes(context, attrs);
        updateStyles();
    }

    private void updateStyles() {
        tvRedPoint.setVisibility(mShowPoint ? VISIBLE : GONE);
        ivArrow.setVisibility(mShowArrow ? VISIBLE : GONE);
        vDivider.setVisibility(mShowDivider ? VISIBLE : GONE);
        tvTitle.setText(mTitleText == null ? "" : mTitleText);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StaticItemLayout);
        mShowPoint = ta.getBoolean(R.styleable.StaticItemLayout_si_point_show, false);
        mShowArrow = ta.getBoolean(R.styleable.StaticItemLayout_si_arrow_show, false);
        mShowDivider = ta.getBoolean(R.styleable.StaticItemLayout_si_divider_show, false);
        mTitleText = ta.getString(R.styleable.StaticItemLayout_si_title_text);
        ta.recycle();
    }

    private void findView() {
        tvRedPoint = findViewById(R.id.tv_redPoint);
        tvTitle = findViewById(R.id.tv_title);
        tvInfo = findViewById(R.id.tv_info);
        ivArrow = findViewById(R.id.iv_arrow);
        vDivider = findViewById(R.id.v_divider);
    }
}