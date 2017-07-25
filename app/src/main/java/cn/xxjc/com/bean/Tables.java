package cn.xxjc.com.bean;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class Tables implements Comparable {
    public int id;
    public String tableName;
    public int state;
    public Tables(){}
    public Tables(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        if(o!=null&&o instanceof Tables){
            return this.id-((Tables) o).id;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tables)) return false;

        Tables tables = (Tables) o;

        return id == tables.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
