package cn.xxjc.com.utils;

import android.text.TextUtils;


import java.util.ArrayList;

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
        for(TableColsValue value:XmlParseUtil.tableColsValues){
            if(value.tableId==tableId){
                values.add(value);
            }
        }
        return values;
    }
    public static ArrayList<TableCols> getTableColsInTable(int tableId,int viliable){
        ArrayList<TableCols> values=new ArrayList<TableCols>();
        for(TableCols value:XmlParseUtil.tableCols){
            if(viliable==0){
                if(value.tableId==tableId&&value.type==3){
                    values.add(value);
                }
            }else{
                if(value.tableId==tableId&&value.type!=3){
                    values.add(value);
                }
            }

        }
        return values;
    }

    public static double getValueByTableColsId(int id){
        for(TableColsValue tableColsValue:XmlParseUtil.tableColsValues){
            if(tableColsValue.colId==id){
                return tableColsValue.colValue;
            }
        }
        return 0;
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
}
