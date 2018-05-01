package com.example.wangjunjie.awesomeproject1.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.example.wangjunjie.awesomeproject1.R;


public class FixRefreshLayout extends SwipeRefreshLayout {
    public FixRefreshLayout(Context context) {
        this(context, null);
    }

    public FixRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        recolor();
    }

    private void recolor(){
        setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

    }
}
