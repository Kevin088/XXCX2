/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.xxjc.com.utils;

import android.content.Context;

import java.io.File;

public class FileUtil {
    public static File getSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "pic.jpg");
        return file;
    }

    public static String xmlPath="/storage/emulated/0/XXCJ/"+Utils.getVersion()+"/";
    public static String xmlName1="Definition.xml";
    public static String xmlName2_R= "datasheet_right.xml";
    public static String xmlName2_E= "datasheet_error.xml";

    public static String result_txt_R= "正常报告-电容器组.doc";
    public static String result_txt_E= "异常报告-电容器组.doc";

    public static String result_txt_001= "001.doc";
    public static String result_txt_002= "002.doc";
}
