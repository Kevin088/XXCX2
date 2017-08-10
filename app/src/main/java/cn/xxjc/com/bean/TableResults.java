package cn.xxjc.com.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/8/1.
 */
@Entity
public class TableResults {
    /**
     * 站名-运行编号-试验单位-试验性质-[试验日期]
     */
    @Id(autoincrement = true)
    private Long id;
    private String zhanming;
    private String yunxingbianhao;
    private String shiyandanwei;
    private String shiyanxingzhi;
    private Date testTime;
    private int state;
    private String otherInfo;
    @Generated(hash = 950466515)
    public TableResults(Long id, String zhanming, String yunxingbianhao,
            String shiyandanwei, String shiyanxingzhi, Date testTime, int state,
            String otherInfo) {
        this.id = id;
        this.zhanming = zhanming;
        this.yunxingbianhao = yunxingbianhao;
        this.shiyandanwei = shiyandanwei;
        this.shiyanxingzhi = shiyanxingzhi;
        this.testTime = testTime;
        this.state = state;
        this.otherInfo = otherInfo;
    }
    @Generated(hash = 570877785)
    public TableResults() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getZhanming() {
        return this.zhanming;
    }
    public void setZhanming(String zhanming) {
        this.zhanming = zhanming;
    }
    public String getYunxingbianhao() {
        return this.yunxingbianhao;
    }
    public void setYunxingbianhao(String yunxingbianhao) {
        this.yunxingbianhao = yunxingbianhao;
    }
    public String getShiyandanwei() {
        return this.shiyandanwei;
    }
    public void setShiyandanwei(String shiyandanwei) {
        this.shiyandanwei = shiyandanwei;
    }
    public String getShiyanxingzhi() {
        return this.shiyanxingzhi;
    }
    public void setShiyanxingzhi(String shiyanxingzhi) {
        this.shiyanxingzhi = shiyanxingzhi;
    }
    public Date getTestTime() {
        return this.testTime;
    }
    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getOtherInfo() {
        return this.otherInfo;
    }
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

}
