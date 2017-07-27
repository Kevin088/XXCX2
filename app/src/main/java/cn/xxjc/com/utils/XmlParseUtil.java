package cn.xxjc.com.utils;

import android.util.Xml;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.xxjc.com.bean.TableCols;
import cn.xxjc.com.bean.TableColsValue;
import cn.xxjc.com.bean.Tables;
import cn.xxjc.com.bean.User;
import cn.xxjc.com.bean.User2Tables;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class XmlParseUtil {
    public static List<User> users=new ArrayList<User>();
    public static List<Tables> tables=new ArrayList<Tables>();
    public static List<TableCols> tableCols=new ArrayList<TableCols>();
    public static List<User2Tables> user2Tables=new ArrayList<User2Tables>();
    public static List<TableColsValue> tableColsValues_R=new ArrayList<TableColsValue>();
    public static List<TableColsValue> tableColsValues_E=new ArrayList<TableColsValue>();
    public static void getDataFromDefinitionXml() throws XmlPullParserException, IOException {
        User user=null;
        Tables table=null;
        TableCols tableCol=null;
        User2Tables user2Table=null;
        int parseIndex=0;

        InputStream inputStream=new FileInputStream(FileUtil.xmlPath+FileUtil.xmlName1);
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(inputStream, "UTF-8"); //为Pull解释器设置要解析的XML数据
        int event = pullParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT){
            switch (event) {
                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:
                    //表一
                    if ("user".equals(pullParser.getName())){
                        user = new User();
                        parseIndex=0;
                    }
                    if(parseIndex==0){
                        if("id".equals(pullParser.getName())){
                            user.id= Integer.parseInt(pullParser.nextText());
                        }
                        if("username".equals(pullParser.getName())){
                            user.userName=pullParser.nextText();
                        }
                        if("name".equals(pullParser.getName())){
                            user.name=pullParser.nextText();
                        }
                        if("psw".equals(pullParser.getName())){
                            user.pwd=pullParser.nextText();
                        }
                    }
                    //表二
                    if ("user2tables".equals(pullParser.getName())){
                        user2Table = new User2Tables();
                        parseIndex=1;
                    }
                    if(parseIndex==1){
                        if("id".equals(pullParser.getName())){
                            user2Table.id= Integer.parseInt(pullParser.nextText());
                        }
                        if("uid".equals(pullParser.getName())){
                            user2Table.uId= Integer.parseInt(pullParser.nextText());
                        }
                        if("tableId".equals(pullParser.getName())){
                            user2Table.tableId= Integer.parseInt(pullParser.nextText());
                        }
                    }
                    //表三
                    if ("tables".equals(pullParser.getName())){
                        table = new Tables();
                        parseIndex=2;
                    }
                    if(parseIndex==2){
                        if("id".equals(pullParser.getName())){
                            table.id= Integer.parseInt(pullParser.nextText());
                        }
                        if("tablename".equals(pullParser.getName())){
                            table.tableName= pullParser.nextText();
                        }
                        if("state".equals(pullParser.getName())){
                            table.state= Integer.parseInt(pullParser.nextText());
                        }
                    }
                    //表四
                    if ("tableCols".equals(pullParser.getName())){
                        tableCol = new TableCols();
                        parseIndex=3;
                    }
                    if(parseIndex==3){
                        if("id".equals(pullParser.getName())){
                            tableCol.id= Integer.parseInt(pullParser.nextText());
                        }
                        if("tColName".equals(pullParser.getName())){
                            tableCol.tColName= pullParser.nextText();
                        }
                        if("tableId".equals(pullParser.getName())){
                            tableCol.tableId= Integer.parseInt(pullParser.nextText());
                        }
                        if("minNum".equals(pullParser.getName())){
                            tableCol.minNum= Integer.parseInt(pullParser.nextText());
                        }
                        if("maxNum".equals(pullParser.getName())){
                            tableCol.maxNum= Integer.parseInt(pullParser.nextText());
                        }
                        if("type".equals(pullParser.getName())){
                            tableCol.type= Integer.parseInt(pullParser.nextText());
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ("user".equals(pullParser.getName())){
                        users.add(user);
                        user = null;
                    }
                    if ("user2tables".equals(pullParser.getName())){
                        user2Tables.add(user2Table);
                        user2Table = null;
                    }
                    if ("tables".equals(pullParser.getName())){
                        tables.add(table);
                        table = null;
                    }
                    if ("tableCols".equals(pullParser.getName())){
                        tableCols.add(tableCol);
                        tableCol = null;
                    }
                    break;

            }

            event = pullParser.next();
        }
    }

    public static void getDataFromDataSheetXml_R() throws XmlPullParserException, IOException {
        TableColsValue tableColsValue=null;
        XmlPullParser pullParser = Xml.newPullParser();
        InputStream inputStream1=new FileInputStream(FileUtil.xmlPath+FileUtil.xmlName2_R);
        pullParser.setInput(inputStream1, "UTF-8"); //为Pull解释器设置要解析的XML数据
        int event = pullParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT){
            switch (event) {
                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:
                    //表一
                    if ("tableCols".equals(pullParser.getName())){
                        tableColsValue = new TableColsValue();
                    }
                    if("id".equals(pullParser.getName())){
                        tableColsValue.id= Integer.parseInt(pullParser.nextText());
                    }
                    if("tableId".equals(pullParser.getName())){
                        tableColsValue.tableId= Integer.parseInt(pullParser.nextText());
                    }
                    if("colId".equals(pullParser.getName())){
                        tableColsValue.colId= Integer.parseInt(pullParser.nextText());
                    }
                    if("tablename".equals(pullParser.getName())){
                        tableColsValue.tablename=pullParser.nextText();
                    }
                    if("colValue".equals(pullParser.getName())){
                        tableColsValue.colValue= Double.parseDouble(pullParser.nextText());
                    }

                    break;

                case XmlPullParser.END_TAG:
                    if ("tableCols".equals(pullParser.getName())){
                        tableColsValues_R.add(tableColsValue);
                        tableColsValue = null;
                    }
                    break;

            }

            event = pullParser.next();
        }
    }
    public static void getDataFromDataSheetXml_E() throws XmlPullParserException, IOException {
        TableColsValue tableColsValue=null;
        XmlPullParser pullParser = Xml.newPullParser();
        InputStream inputStream1=new FileInputStream(FileUtil.xmlPath+FileUtil.xmlName2_E);
        pullParser.setInput(inputStream1, "UTF-8"); //为Pull解释器设置要解析的XML数据
        int event = pullParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT){
            switch (event) {
                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:
                    //表一
                    if ("tableCols".equals(pullParser.getName())){
                        tableColsValue = new TableColsValue();
                    }
                    if("id".equals(pullParser.getName())){
                        tableColsValue.id= Integer.parseInt(pullParser.nextText());
                    }
                    if("tableId".equals(pullParser.getName())){
                        tableColsValue.tableId= Integer.parseInt(pullParser.nextText());
                    }
                    if("colId".equals(pullParser.getName())){
                        tableColsValue.colId= Integer.parseInt(pullParser.nextText());
                    }
                    if("tablename".equals(pullParser.getName())){
                        tableColsValue.tablename=pullParser.nextText();
                    }
                    if("colValue".equals(pullParser.getName())){
                        tableColsValue.colValue= Double.parseDouble(pullParser.nextText());
                    }

                    break;

                case XmlPullParser.END_TAG:
                    if ("tableCols".equals(pullParser.getName())){
                        tableColsValues_E.add(tableColsValue);
                        tableColsValue = null;
                    }
                    break;

            }

            event = pullParser.next();
        }
    }

    public static void clearSize(){
        tableCols.clear();
        user2Tables.clear();
        users.clear();
        tables.clear();
        tableColsValues_R.clear();
        tableColsValues_E.clear();
    }
    public static void rankData(){
        Collections.sort(tableCols);
        Collections.sort(user2Tables);
        Collections.sort(users);
        Collections.sort(tables);
        Collections.sort(tableColsValues_R);
        Collections.sort(tableColsValues_E);
    }


    public static void saveDataSheetXml() throws Exception {
        OutputStream out=new FileOutputStream(FileUtil.xmlPath+FileUtil.xmlName2_R);
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(out, "UTF-8");
        serializer.startDocument("UTF-8", true);
        serializer.startTag(null, "NewDataSet");
        for (TableColsValue value : tableColsValues_R) {
            serializer.startTag(null, "tableCols");

            serializer.startTag(null, "id");
            serializer.text(value.id+"");
            serializer.endTag(null, "id");

            serializer.startTag(null, "tableId");
            serializer.text(value.tableId+"");
            serializer.endTag(null, "tableId");

            serializer.startTag(null, "colId");
            serializer.text(value.colId+"");
            serializer.endTag(null, "colId");

            serializer.startTag(null, "tablename");
            serializer.text(value.tablename+"");
            serializer.endTag(null, "tablename");

            serializer.startTag(null, "colValue");
            serializer.text(value.colValue+"");
            serializer.endTag(null, "colValue");

            serializer.endTag(null, "tableCols");
        }
        serializer.endTag(null, "NewDataSet");
        serializer.endDocument();
        out.flush();
        out.close();
    }
    public static void saveDefinitionXml() throws Exception {
        OutputStream out=new FileOutputStream(FileUtil.xmlPath+FileUtil.xmlName1);
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(out, "UTF-8");
        serializer.startDocument("UTF-8", true);
        serializer.startTag(null, "NewDataSet");
        for (User user : users) {
            serializer.startTag(null, "user");

            serializer.startTag(null, "id");
            serializer.text(user.id+"");
            serializer.endTag(null, "id");

            serializer.startTag(null, "username");
            serializer.text(user.userName+"");
            serializer.endTag(null, "username");

            serializer.startTag(null, "name");
            serializer.text(user.name+"");
            serializer.endTag(null, "name");

            serializer.startTag(null, "psw");
            serializer.text(user.pwd+"");
            serializer.endTag(null, "psw");

            serializer.endTag(null, "user");
        }
        for (User2Tables user2Table : user2Tables) {
            serializer.startTag(null, "user2tables");

            serializer.startTag(null, "id");
            serializer.text(user2Table.id+"");
            serializer.endTag(null, "id");

            serializer.startTag(null, "uid");
            serializer.text(user2Table.uId+"");
            serializer.endTag(null, "uid");

            serializer.startTag(null, "tableId");
            serializer.text(user2Table.tableId+"");
            serializer.endTag(null, "tableId");

            serializer.endTag(null, "user2tables");
        }

        for (Tables table : tables) {
            serializer.startTag(null, "tables");

            serializer.startTag(null, "id");
            serializer.text(table.id+"");
            serializer.endTag(null, "id");

            serializer.startTag(null, "tablename");
            serializer.text(table.tableName+"");
            serializer.endTag(null, "tablename");

            serializer.startTag(null, "state");
            serializer.text(table.state+"");
            serializer.endTag(null, "state");

            serializer.endTag(null, "tables");
        }

        for (TableCols tableCol : tableCols) {
            serializer.startTag(null, "tableCols");

            serializer.startTag(null, "id");
            serializer.text(tableCol.id+"");
            serializer.endTag(null, "id");

            serializer.startTag(null, "tColName");
            serializer.text(tableCol.tColName+"");
            serializer.endTag(null, "tColName");

            serializer.startTag(null, "tableId");
            serializer.text(tableCol.tableId+"");
            serializer.endTag(null, "tableId");

            serializer.startTag(null, "minNum");
            serializer.text(tableCol.minNum+"");
            serializer.endTag(null, "minNum");

            serializer.startTag(null, "maxNum");
            serializer.text(tableCol.maxNum+"");
            serializer.endTag(null, "maxNum");

            serializer.startTag(null, "type");
            serializer.text(tableCol.type+"");
            serializer.endTag(null, "type");

            serializer.endTag(null, "tableCols");
        }

        serializer.endTag(null, "NewDataSet");
        serializer.endDocument();
        out.flush();
        out.close();
    }
}
