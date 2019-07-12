package com.watayouxiang.widgetlibrary;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * 精简自：https://github.com/sunfusheng/MarqueeView
 */
public class NoticeFlipper extends ViewFlipper {
    private List<? extends CharSequence> mNotices = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private boolean mIsAnimStart = false;
    private int mPosition = 0;

    public NoticeFlipper(Context context) {
        super(context);
    }

    public NoticeFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置公告点击监听
     *
     * @param listener 监听器
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 入口方法
     * 设置公告数据，并开始轮播
     *
     * @param notices 公告数据
     */
    public void startWithList(List<? extends CharSequence> notices) {
        addNotices(notices);
        postStartNotices();
    }

    //==============================================================================================

    /**
     * 添加公告数据
     *
     * @param notices 公告数据
     */
    private void addNotices(List<? extends CharSequence> notices) {
        if (notices == null) {
            throw new RuntimeException("Please set the value of notices!");
        }
        mNotices = notices;
    }

    /**
     * 开启公告
     * 运行在主线程，避免初始化时动画显示不正常
     */
    private void postStartNotices() {
        post(new Runnable() {
            @Override
            public void run() {
                startNotices();
            }
        });
    }

    /**
     * 开启公告
     */
    private void startNotices() {
        TextView noticeView = getNoticeView(0);
        if (noticeView == null) {
            return;
        }
        removeAllViews();
        clearAnimation();
        mPosition = 0;

        //添加视图
        addView(noticeView);
        //设置时间间隔
        setFlipInterval(3000);
        //设置进出动画
        setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_bottom_in));
        setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_top_out));
        //设置进入动画监听（该动画是重复执行的）
        getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (mIsAnimStart) {
                    animation.cancel();
                }
                mIsAnimStart = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mPosition++;
                if (mPosition >= mNotices.size()) {
                    mPosition = 0;
                }
                TextView noticeView = getNoticeView(mPosition);
                //只有是新创建的视图，才会添加到父视图中
                if (noticeView.getParent() == null) {
                    addView(noticeView);
                }
                mIsAnimStart = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //开启轮播
        startFlipping();
    }

    /**
     * 获取公告视图
     * 前三个视图会创建，之后复用之前创建的视图。
     *
     * @param position 公告位置
     * @return 公告视图
     */
    private TextView getNoticeView(int position) {
        return getNoticeView(getNotice(position), position);
    }

    /**
     * 获取公告视图。
     * 前三个视图会创建，之后复用之前创建的视图。
     *
     * @param notice   公告数据
     * @param position 公告位置
     * @return 公告视图
     */
    private TextView getNoticeView(final CharSequence notice, final int position) {
        if (notice == null) {
            return null;
        }
        //最多添加3个，不断复用
        int displayedChildIndex = getDisplayedChild();
        int childIndex = (displayedChildIndex + 1) % 3;
        TextView textView = (TextView) getChildAt(childIndex);
        if (textView == null) {
            textView = new TextView(getContext());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
            textView.setTextColor(Color.parseColor("#1A1A1A"));
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(Gravity.CENTER_VERTICAL);
        }
        textView.setText(notice);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onNoticeClick(position, (TextView) v);
                }
            }
        });
        return textView;
    }

    /**
     * 获取指定位置的公告
     *
     * @param position 公告数据
     * @return 公告数据
     */
    private CharSequence getNotice(int position) {
        int size = mNotices.size();
        if (size > 0 && position >= 0 && position < size) {
            return mNotices.get(position);
        }
        return null;
    }

    public interface OnItemClickListener {
        /**
         * 公告item点击回调
         *
         * @param position 公告位置
         * @param textView 公告视图的TextView
         */
        void onNoticeClick(int position, TextView textView);
    }
}
