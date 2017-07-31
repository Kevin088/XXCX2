package cn.xxjc.com.adapter;

import android.content.Context;
import android.view.View;


import java.util.ArrayList;

import cn.xxjc.com.R;
import cn.xxjc.com.app.App;
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
        holder.setTextViewText(R.id.tv_name,"相关报告类型");
    }
}
