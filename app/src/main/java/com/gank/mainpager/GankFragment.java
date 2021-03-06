package com.gank.mainpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gank.R;
import com.gank.adapter.GankNewsAdapter;
import com.gank.bean.GankNews;
import com.gank.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Swy on 2017/3/4.
 */

public class GankFragment extends Fragment implements GankContract.View {

    private GankNewsAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;//目前fab还没有设置作用
    private SwipeRefreshLayout refresh;

    private GankContract.Presenter presenter;
    private Activity mActivity;

    public GankFragment() {
    }

    //
    public static GankFragment newInstance() {

        return new GankFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);
        initView(view);
        presenter.start();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.reflush();
            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isScrollState=false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager= (LinearLayoutManager) recyclerView.getLayoutManager();
                //没有滚动时候
                if (newState==RecyclerView.SCROLL_STATE_IDLE){
                    //获的最后一个可见的item
                    int lastVisibilityItem=manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount=manager.getItemCount();

                    //判断是否滚动到底部并且是向下滑动
                    if (lastVisibilityItem==(totalItemCount-1)&&isScrollState){
                        presenter.loadMore(1);
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isScrollState=dy>0;
                //隐藏或者显示fab
//                if (dy>0){
//                    fab.hide();
//                }else {
//                    fab.show();
//                }
            }
        });
        // 按通常的做法，在每个fragment中去设置监听时间会导致先前设置的listener失效
        // 尝试将监听放置到main pager adapter中，这样做会引起fragment中recycler view和fab的监听冲突
        // fab并不能获取到点击事件
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
        return view;
    }

    @Override
    public void showError() {
        Snackbar.make(fab, R.string.loaded_failed,Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.reflush();
                    }
                })
                .show();
    }

    @Override
    public void showNotNetError() {
        Snackbar.make(fab, R.string.not_net_work, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(true);
            }
        });
    }

    @Override
    public void Stoploading() {
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void showResult(ArrayList<GankNews.Question> list) {
        if (adapter==null){
            Log.i(TAG, "showResult: "+list.size());
            adapter=new GankNewsAdapter(list,getContext());
            adapter.setItemOnClickListener(new OnRecyclerViewOnClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    presenter.StartReading(position);
                }

                @Override
                public void onItemLongClick(View v, int position) {

                }
            });
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void setPresenter(GankContract.Presenter presenter) {
        if (presenter!=null){
            this.presenter=presenter;
        }
    }

    @Override
    public void initView(View view) {
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refresh= (SwipeRefreshLayout) view.findViewById(R.id.refreshlayout);
        //设置下拉刷新的按钮的颜色
        refresh.setColorSchemeResources(R.color.colorPrimary);

        fab = (FloatingActionButton) mActivity.findViewById(R.id.fab);
        fab.setRippleColor(getResources().getColor(R.color.colorPrimaryDark));
    }
}
