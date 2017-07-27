package cn.xxjc.com.bean;


import java.util.ArrayList;

import cn.xxjc.com.utils.XmlParseUtil;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class User2Tables implements Comparable {
    public int id;
    public int uId;
    public int tableId;


    @Override
    public int compareTo(Object o) {
        if(o!=null&&o instanceof User2Tables){
            return this.id-((User2Tables) o).id;
        }
        return 0;
    }
    public static ArrayList<Tables> getUserTables(int userId){
        ArrayList<Tables> tables=new ArrayList<>();
        ArrayList<Integer> tableIDs=new ArrayList<Integer>();
        for(User2Tables user2Table: XmlParseUtil.user2Tables){
            if(user2Table.uId==userId)
                tableIDs.add(user2Table.tableId);
        }
        for(Integer id:tableIDs){
            int index = XmlParseUtil.tables.indexOf(new Tables(id));
            if(index!=-1){
                tables.add(XmlParseUtil.tables.get(index));
            }

        }
        return tables;
    }
}
