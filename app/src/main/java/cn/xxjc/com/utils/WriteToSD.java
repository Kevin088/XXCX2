package cn.xxjc.com.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.xxjc.com.app.App;

/*将assets文件夹下的数据库写入SD卡中
 * @author Dave */
public class WriteToSD {


    public static void WriteToSD(String filePath, String fileName) {
        if(isExist(filePath,fileName)) return;
        InputStream inputStream;
        try {
            inputStream = App.getContext().getResources().getAssets().open(fileName);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + "/"+fileName);
            byte[] buffer = new byte[1024*8];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            System.out.println("success    =================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isExist(String filePath, String fileName) {
        File file = new File(filePath + "/"+fileName);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}