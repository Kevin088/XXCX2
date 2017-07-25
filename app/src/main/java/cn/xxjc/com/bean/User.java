package cn.xxjc.com.bean;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class User implements Comparable {
    public int id;
    public String userName;
    public String name;
    public String pwd;

    @Override
    public int compareTo(Object o) {
        if(o!=null&&o instanceof User){
            return this.id-((User) o).id;
        }
        return 0;
    }
}
