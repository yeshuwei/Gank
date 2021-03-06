package com.gank.mainpager;

import com.gank.BasePresenter;
import com.gank.BaseView;
import com.gank.bean.FrontNews;

import java.util.ArrayList;

/**
 * Created by Swy on 2017/3/18.
 */

public interface FrontContract {
    interface View extends BaseView<FrontContract.Presenter> {
        void showError();
        void showLoading();
        void Stoploading();
        void showResult(ArrayList<FrontNews.Question> list);
        void showNotNetError();

    }
    interface Presenter extends BasePresenter {
        // 请求数据
        void loadPosts(int PagerNum, boolean cleaing);
        //刷新数据
        void  reflush();
        //加载更多
        void loadMore(int PagerNum);
        //显示详情
        void StartReading(int positon);
        //随便看
        void LookAround();
    }
}
