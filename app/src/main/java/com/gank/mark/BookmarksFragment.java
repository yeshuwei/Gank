package com.gank.mark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gank.R;
import com.gank.adapter.BookMarksAdapter;
import com.gank.bean.BaseBean;
import com.gank.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;

/**
 * Created by Swy on 2017/3/4.
 */

public class BookmarksFragment extends Fragment implements BookmarksContract.View {
    private BookMarksAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mMarkContentLayout;
    private BookmarksContract.Presenter presenter;
    private LinearLayout mNoDataLayout;

    public BookmarksFragment() {

    }

    public static BookmarksFragment newInstance() {

        return new BookmarksFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mark_layout, container, false);
        initView(view);
        presenter.loadResults(false);
        mMarkContentLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadResults(true);
            }
        });
        return view;
    }

    @Override
    public void showResults(ArrayList<BaseBean> newsList) {
        if (newsList.size() <= 0) {
            //显示无数据界面
            mNoDataLayout.setVisibility(View.VISIBLE);
            mMarkContentLayout.setVisibility(View.GONE);
        } else {
            mNoDataLayout.setVisibility(View.GONE);
            mMarkContentLayout.setVisibility(View.VISIBLE);
        }
        if (adapter == null) {
            adapter = new BookMarksAdapter(getActivity(), newsList);
            adapter.setItemOnClickListener(new OnRecyclerViewOnClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    int type = recyclerView.findViewHolderForLayoutPosition(position).getItemViewType();
                    if (type == BookMarksAdapter.TYPE_Gank_NORMAL) {
                        presenter.startReading(position);
                    } else if (type == BookMarksAdapter.TYPE_Front_NORMAL) {
                        presenter.startReading(position);
                    } else if (type == BookMarksAdapter.TYPE_IOS_NORMAL) {
                        presenter.startReading(position);
                    }
                }

                @Override
                public void onItemLongClick(View v, int position) {

                }
            });
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void notifyDataChanged() {
        presenter.loadResults(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        mMarkContentLayout.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        mMarkContentLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(BookmarksContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMarkContentLayout = (SwipeRefreshLayout) view.findViewById(R.id.mark_content);
        mMarkContentLayout.setColorSchemeResources(R.color.colorPrimary);
        mNoDataLayout = (LinearLayout) view.findViewById(R.id.no_data_layout);
    }
}
