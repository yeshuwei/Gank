package com.gank.util;

/**
 * Created by 11033 on 2017/3/4.
 */

public class Api {

    /**
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
       请求个数： 数字，大于0
       第几页：数字，大于0
       默认自动加载第一页 如果上拉加载则加载下一页

     */
    public static final String Gank_Android="http://gank.io/api/data/Android/10/";

    /**
     * 随机数据：http://gank.io/api/random/data/分类/个数
     数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
     个数： 数字，大于0
     目前随机数据来源当前列表
     */
    public static final String Gank_Android_Look_Around="http://gank.io/api/random/data/Android/";
}