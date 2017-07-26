package cn.xxjc.com.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import cn.xxjc.com.utils.FileUtil;
import cn.xxjc.com.utils.WriteToSD;
import cn.xxjc.com.utils.XmlParseUtil;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class App extends Application {
    public static Context context;
    public static int clickCount=-1;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

        new Thread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }).start();

    }
    public void initData(){
        try {
            WriteToSD.WriteToSD(FileUtil.xmlPath, FileUtil.xmlName1);
            WriteToSD.WriteToSD(FileUtil.xmlPath, FileUtil.xmlName2_R);
            WriteToSD.WriteToSD(FileUtil.xmlPath, FileUtil.xmlName2_E);
            XmlParseUtil.clearSize();
            XmlParseUtil.getDataFromDefinitionXml();
            XmlParseUtil.getDataFromDataSheetXml_R();
            XmlParseUtil.getDataFromDataSheetXml_E();
            XmlParseUtil.rankData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Context getContext(){
        return context;
    }

    public static boolean isActivityOnForeground(Activity activity) {
        return isActivityOnForeground(activity.getClass().getName());
    }

    public static boolean isActivityOnForeground(String activity) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // activity位于堆栈的顶层
            if (activity.equals(tasksInfo.get(0).topActivity.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public static boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // 应用程序位于堆栈的顶层
            if (getPackageName2().equals(
                    tasksInfo.get(0).topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static String getPackageName2() {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context
                    .getPackageName(), 0);
            String packageName = info.packageName;
            return packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
