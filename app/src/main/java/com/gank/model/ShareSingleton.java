package com.gank.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.gank.interfaze.MyQQListener;
import com.gank.util.Constants;
import com.gank.util.wxUtil.Util;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

/**
 * Created by Swy on 2017/8/10.
 */

public class ShareSingleton {

    private Tencent mTencent;
    public static IWXAPI api;

    private static final int THUMB_SIZE = 150;
    /**
     * 纯图片分享 传送网络图片url
     * !! 分享操作要在主线程中完成
     * @param activity
     * @param netUrl 图片本地地址
     * @param appName appName
     * @param shareToQQExtInt 分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮。
     * @param listener 分享回调接口
     */
    public void shareImgToQQ(Activity activity, String netUrl, @StringRes int appName, int shareToQQExtInt, MyQQListener listener){
        if (mTencent==null){
            mTencent=Tencent.createInstance(Constants.QQ_APP_ID,activity.getApplicationContext());
        }
        Bundle params=new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,netUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, activity.getString(appName));
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, shareToQQExtInt);
        mTencent.shareToQQ(activity,params,listener);
    }

    /**
     * 纯图片分享 传送本地图片地址
     * !! 分享操作要在主线程中完成
     * @param activity
     * @param localUrl 图片本地地址
     * @param appName appName
     * @param shareToQQExtInt 分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
    QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
    QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮。
     * @param listener 分享回调接口
     */
    public void shareLocalImgToQQ(Activity activity, String localUrl, @StringRes int appName, int shareToQQExtInt, MyQQListener listener){
        if (mTencent==null){
            mTencent=Tencent.createInstance(Constants.QQ_APP_ID,activity.getApplicationContext());
        }
        Bundle params=new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,localUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, activity.getString(appName));
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, shareToQQExtInt);
        mTencent.shareToQQ(activity,params,listener);
    }


    /**
     * 图文分享 图片来源网络
     * !! 分享操作要在主线程中完成
     * @param activity
     * @param targetUrl  这条分享消息被好友点击后的跳转URL。
     * @param shareTitle 	分享的标题, 最长30个字符。
     * @param shareSummary 分享的消息摘要，最长40个字。
     * @param netImgUrl 可填 分享图片的URL或者本地路径
     * @param appName 手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     * @param shareToQQExtInt 额外选项  是否自动打开分享到QZone的对话框
     * @param listener 分享回调接口
     */
    public void shareToQQ(Activity activity,String targetUrl,String shareTitle,String shareSummary,
                          @Nullable String netImgUrl,@StringRes int appName,int shareToQQExtInt,MyQQListener listener){
        if (mTencent==null){
            mTencent=Tencent.createInstance(Constants.QQ_APP_ID,activity.getApplicationContext());
        }
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,targetUrl);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitle);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareSummary );
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,  netImgUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,activity.getString(appName));
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  shareToQQExtInt);
        mTencent.shareToQQ(activity, params, listener);
    }

    /**
     * 图文分享 图片来源本地
     * !! 分享操作要在主线程中完成
     * @param activity
     * @param targetUrl  这条分享消息被好友点击后的跳转URL。
     * @param shareTitle 	分享的标题, 最长30个字符。
     * @param shareSummary 分享的消息摘要，最长40个字。
     * @param localImgUrl 可填 分享图片的URL或者本地路径
     * @param appName 手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     * @param shareToQQExtInt 额外选项  是否自动打开分享到QZone的对话框
     * @param listener 分享回调接口
     */
    public void shareLoaclMsgToQQ(Activity activity,String targetUrl,String shareTitle,String shareSummary,
                          @Nullable String localImgUrl,@StringRes int appName,int shareToQQExtInt,MyQQListener listener){
        if (mTencent==null){
            mTencent=Tencent.createInstance(Constants.QQ_APP_ID,activity.getApplicationContext());
        }
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,targetUrl);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitle);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareSummary );
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,  localImgUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,activity.getString(appName));
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  shareToQQExtInt);
        mTencent.shareToQQ(activity, params, listener);
    }


    /**
     * 分享图片到微信或者
     * @param context
     * @param bmp 分享的图片
     * @param isShareFriend isShareFriend true 分享到朋友，false分享到朋友圈
     */
    public void shareImgToWx(Context context,Bitmap bmp, boolean isShareFriend){
//        注册操作也可以写死在Application中
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api=WXAPIFactory.createWXAPI(context,Constants.WX_APP_ID,true);
        // 将该app注册到微信
        api.registerApp(Constants.WX_APP_ID);

        //初始化WXImageObject和WXMediaMessage对象
        WXImageObject imgObj=new WXImageObject(bmp);
        WXMediaMessage bitmapMsg=new WXMediaMessage();
        bitmapMsg.mediaObject=imgObj;

        //设置缩略图
        Bitmap thumbBmp=Bitmap.createScaledBitmap(bmp,THUMB_SIZE,THUMB_SIZE,true);
        bmp.recycle();
        bitmapMsg.thumbData= Util.bmpToByteArray(thumbBmp,true);

        //构造一个Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("img");//transaction 字段用于唯一标识一个请求
        req.message=bitmapMsg;
        req.scene=isShareFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private String getTransaction() {
        try {
//            final GetMessageFromWX.Req req = new GetMessageFromWX.Req(bundle);
//            return req.transaction;
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    private ShareSingleton() {
    }
    public static final ShareSingleton getInstance(){
        return Singleton.INSTANCE;
    }
    private static class Singleton{
        private static final ShareSingleton INSTANCE=new ShareSingleton();
    }

}
