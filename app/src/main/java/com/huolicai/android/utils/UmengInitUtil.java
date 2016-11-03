package com.huolicai.android.utils;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.huolicai.android.R;
import com.huolicai.android.activity.home.HomeActivity;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * @author: guoyazhou
 * @date: 2016-04-01 15:42
 */
public class UmengInitUtil {

    private static final String TAG = "UmengInitUtil";
    //新项目推送
    public static final String UMENG_ACTIVITY_PROJECT_LIST = "activity_project_list";
    //	积分商城
    public static final String UMENG_ACTIVITY_INTEGRAL = "activity_user_integral";
    //	新消息
    public static final String UMENG_ACTIVITY_MESSAGE_LIST = "activity_message_list";
    //	新公告
    public static final String UMENG_ACTIVITY_NOTICE_LIST = "activity_notice_list";
    //	火券列表
    public static final String UMENG_ACTIVITY_COUPON_LIST = "activity_coupon_list";


    public static void umengMessageInit(Context context) {
        PushAgent mPushAgent = PushAgent.getInstance(context);
        mPushAgent.enable();
        mPushAgent.setDebugMode(false);
        String currChannel = mPushAgent.getMessageChannel();

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 参考集成文档的1.6.3 http://dev.umeng.com/push/android/integration#1_6_3
             * */
            @Override
            public void dealWithCustomMessage(final Context context,
                                              final UMessage msg) {
                new Handler().post(new Runnable() {

                    @Override
                    public void run() {
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            // 自定义消息的点击统计
                            UTrack.getInstance(context).trackMsgClick(msg);
                        } else {
                            // 自定义消息的忽略统计
                            UTrack.getInstance(context).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            /**
             * 参考集成文档的1.6.4 http://dev.umeng.com/push/android/integration#1_6_4
             * */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                                context);
                        RemoteViews myNotificationView = new RemoteViews(
                                context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title,
                                msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text,
                                msg.text);
                        myNotificationView.setImageViewBitmap(
                                R.id.notification_large_icon,
                                getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));

                        builder.setContent(myNotificationView);
                        builder.setAutoCancel(true);
                        builder.setTicker(msg.ticker, myNotificationView);
                        Notification mNotification = builder.build();
                        // 由于Android
                        // v4包的bug，在2.3及以下系统，Builder创建出来的Notification，并没有设置RemoteView，故需要添加此代码
                        mNotification.contentView = myNotificationView;
                        return mNotification;
                    default:
                        // 默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 该Handler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK 参考集成文档的1.6.2
         * http://dev.umeng.com/push/android/integration#1_6_2
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {

                String type = msg.extra.get("type");
                LogUtil.vLogPrint(TAG, "umeng_type=" + type);
                Intent intent = null;

                if (UMENG_ACTIVITY_PROJECT_LIST.equals(type)) {
//                    投资界面
//                    intent = new Intent(context, InvestActivity.class);

                } else if (UMENG_ACTIVITY_INTEGRAL.equals(type)) {

                    intent = new Intent(context, HomeActivity.class);

                } else if (UMENG_ACTIVITY_MESSAGE_LIST.equals(type)) {
//                        消息中心
//                    intent = new Intent(context, MessageCenterActivity.class);
//                    intent.putExtra(type, UMENG_ACTIVITY_MESSAGE_LIST);

                } else if (UMENG_ACTIVITY_NOTICE_LIST.equals(type)) {
//                    公告中心
//                    intent = new Intent(context, MessageCenterActivity.class);
//                    intent.putExtra(type, UMENG_ACTIVITY_NOTICE_LIST);

                } else if (UMENG_ACTIVITY_COUPON_LIST.equals(type)) {
//                    火券列表
//                    intent = new Intent(context, MyFireCouponsActivity.class);
                }

                if (intent != null) {

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    LogUtil.vLogPrint(TAG,"   message_startActivity" + intent);
                }

            }

        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        mPushAgent.onAppStart();
    }

}
