package cn.xxjc.com.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;


import java.util.ArrayList;

import cn.xxjc.com.app.App;
import cn.xxjc.com.bean.TableCols;
import cn.xxjc.com.bean.TableColsValue;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class Utils {
    public static ArrayList<TableColsValue> getTableColsValueInTable(int tableId){
        ArrayList<TableColsValue> values=new ArrayList<TableColsValue>();
        for(TableColsValue value:XmlParseUtil.tableColsValues_R){
            if(value.tableId==tableId){
                values.add(value);
            }
        }
        return values;
    }
    public static ArrayList<TableCols> getTableColsInTable(int tableId){
        ArrayList<TableCols> values=new ArrayList<TableCols>();
        for(TableCols value:XmlParseUtil.tableCols){
            if(value.tableId==tableId){
                values.add(value);
            }
        }
        return values;
    }

    public static int getValueByTableColsId(int id){
        if(App.clickCount%2==0){
            for(TableColsValue tableColsValue:XmlParseUtil.tableColsValues_R){
                if(tableColsValue.colId==id){
                    return tableColsValue.colValue;
                }
            }
        }else{
            for(TableColsValue tableColsValue:XmlParseUtil.tableColsValues_E){
                if(tableColsValue.colId==id){
                    return tableColsValue.colValue;
                }
            }
        }

        return -1111;
    }
    public static double getValueFromStr(String str){
        try {
            if (TextUtils.isEmpty(str)) return 0;
            char[] b = str.toCharArray();
            String result = "";
            for (int i = 0; i < b.length; i++)
            {
                if (("0123456789.").indexOf(b[i] + "") != -1)
                {
                    result += b[i];
                }
            }
            if(TextUtils.isEmpty(result))
                return 0;
            else{
                return Double.parseDouble(result);
            }
        }catch (Exception e){
            return 0;
        }
    }
    /**
     * 将dip转换为pix
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dipToPixels(Context context, float dip) {
        return (int) (context.getResources().getDisplayMetrics().density * dip);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static int sp2px(Context context, float spValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(spValue * scale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }
    /**
     * 获取屏幕高度(像素)
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }
}
