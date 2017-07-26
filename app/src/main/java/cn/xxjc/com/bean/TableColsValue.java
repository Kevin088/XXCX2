package cn.xxjc.com.bean;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class TableColsValue implements Comparable {
    public int id;
    public int tableId;
    public int colId;
    public String tablename;
    public int colValue;
    @Override
    public int compareTo(Object o) {
        if(o!=null&&o instanceof TableColsValue){
            return this.id-((TableColsValue) o).id;
        }
        return 0;
    }
}
