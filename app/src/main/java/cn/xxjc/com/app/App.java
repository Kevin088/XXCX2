package cn.xxjc.com.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;



import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import cn.xxjc.com.dao.DaoMaster;
import cn.xxjc.com.dao.DaoSession;
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
    private DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase db;
    private  DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        setDatabase();
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

            WriteToSD.WriteToSD(FileUtil.xmlPath, FileUtil.result_txt_R);
            WriteToSD.WriteToSD(FileUtil.xmlPath, FileUtil.result_txt_E);

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


    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
    public static SQLiteDatabase getDb() {
        return db;
    }
}
