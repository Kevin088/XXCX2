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
public class HomeAdapter extends CommonAdapter<Integer> {
    public HomeAdapter(Context context, ArrayList<Integer> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Integer item) {
        holder.setTextViewText(R.id.tv_name,"电容器组");
        if(getmDatas().get(position)%2==0){
            holder.setTextViewText(R.id.tv_name_is_right,"正常报告-电容器组");
        }else {
            holder.setTextViewText(R.id.tv_name_is_right,"异常报告-电容器组");
        }
    }
}
