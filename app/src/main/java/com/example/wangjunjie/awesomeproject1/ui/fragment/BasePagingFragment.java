package com.example.wangjunjie.awesomeproject1.ui.fragment;

import android.os.Bundle;;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangjunjie.awesomeproject1.R;
import com.trello.rxlifecycle2.components.support.RxFragment;



public abstract class BasePagingFragment extends RxFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected boolean mLoading;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    public RecyclerView mRecyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return this.onCreateContentView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View pView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(pView, savedInstanceState);
        //绑定ui
        this.mSwipeRefreshLayout=(SwipeRefreshLayout)pView.findViewById(R.id.swipe_refresh_layout);
        this.mRecyclerView = (RecyclerView) pView.findViewById(R.id.recycler_view);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    public View onCreateContentView(final LayoutInflater layoutInflater, @NonNull final ViewGroup container,
                                    @Nullable final Bundle savedInstanceState) {
        return layoutInflater.inflate(onCreatePagingLayoutId(), container, false);
    }

    //返回页面
    protected int onCreatePagingLayoutId(){
        return R.layout.fragment_paging;
    }
    protected abstract void onRefreshing();

    @Override
    public void onRefresh() {
        mLoading = true;
        if (!this.mSwipeRefreshLayout.isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(true);
        }

        mSwipeRefreshLayout.post(() -> {
            onRefreshing();
        });

    }

    protected void postRefreshFailed(Throwable exception) {
        if (exception != null) {
            exception.printStackTrace();
        }
        postRefreshCompleted(null);
    }

    protected void postRefreshCompleted(Object result) {
        postRefreshCompleted(result, false);
    }

    protected void postRefreshCompleted(Object result, boolean fromCache) {
        postRefreshCompleted(result, fromCache, true);
    }

    protected void postRefreshCompleted(Object result, boolean fromCache, boolean stopRefresh) {
        if (getActivity() == null) {
            return;
        }
        if (stopRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if (!fromCache) {
            mLoading = false;
        }


    }


}
