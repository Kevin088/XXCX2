package cn.xxjc.com.bean;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class TableCols implements Comparable {
    public int id;
    public String tColName;
    public int tableId;
    public int minNum;
    public int maxNum;
    public double value=-1111;
    public int tableColsValueId;
    public int type;

    @Override
    public int compareTo(Object o) {
        if(o!=null&&o instanceof TableCols){
            return this.id-((TableCols) o).id;
        }
        return 0;
    }
}
