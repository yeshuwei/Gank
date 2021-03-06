package com.gank.util;

/**
 * Created by Swy on 2017/3/4.
 */

public class Api {

    /**
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
       请求个数： 数字，大于0
       第几页：数字，大于0
       默认自动加载第一页 如果上拉加载则加载下一页

     */
    public static final String GANK_ANDROID="http://gank.io/api/data/Android/10/";

    /**
     * 随机数据：http://gank.io/api/random/data/分类/个数
     数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
     个数： 数字，大于0
     目前随机数据来源当前列表
     */
    public static final String GANK_ANDROID_LOOK_AROUND="http://gank.io/api/random/data/Android/";


    //前端api
    public static final String GANK_FRONT="http://gank.io/api/data/%E5%89%8D%E7%AB%AF/10/";

    //福利
    public static final String GANK_MEIZI="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/";

    //Ios
    public static final String GANK_IOS="http://gank.io/api/data/iOS/10/";

    /**
     * 搜索
     */
    private static final String GANK_SEARCH_HEAD = "http://gank.io/api/search/query/listview/category/Android/count/10/page/1 ";
}
