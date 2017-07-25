package cn.xxjc.com.adapter;

import android.content.Context;


import java.util.ArrayList;

import cn.xxjc.com.R;
import cn.xxjc.com.bean.Tables;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class BaobiaoAdapter extends CommonAdapter<Tables> {
    public BaobiaoAdapter(Context context, ArrayList<Tables> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Tables item) {
        holder.setTextViewText(R.id.tv_name,item.tableName);
        if(item.state==0){
            holder.setTextViewText(R.id.tv_statu,"未采集");
        }else{
            holder.setTextViewText(R.id.tv_statu,"已采集");
        }
    }
}
