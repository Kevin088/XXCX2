package cn.xxjc.com.adapter;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import cn.xxjc.com.R;
import cn.xxjc.com.bean.TableCols;


/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class CollectDataAdapter extends CommonAdapter<TableCols> {
    public interface OnSeeClickBack{
        void onClickBack(int position);
    }
    public OnSeeClickBack onSeeClickBack;



    public CollectDataAdapter(Context context, ArrayList<TableCols> datas, int layoutId) {
        super(context, datas, layoutId);
    }
    @Override
    public void convert(ViewHolder holder, TableCols item) {
        holder.setTextViewText(R.id.tv_name,item.tColName);
        holder.setTextViewText(R.id.tv_data,item.value+"");
        holder.getView(R.id.iv_see).setTag(position);
        holder.setViewOnClickListener(R.id.iv_see, new View.OnClickListener() {
            final int index=position;
            @Override
            public void onClick(View v) {
                if(onSeeClickBack!=null)
                    onSeeClickBack.onClickBack(index);
            }
        });
    }
}
