package cn.xxjc.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import cn.xxjc.com.R;
import cn.xxjc.com.bean.TableResults;
import cn.xxjc.com.utils.Utils;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class Home2Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<TableResults> list;


    public Home2Adapter(Context context, List<TableResults> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }


    public int getCount() {
        return list.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.item_home,
                    parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name_is_right);
            viewHolder.tvNmae=(TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if(list.get(position).getState()==0){
            viewHolder.name.setText("异常报告-电容器组");
        }else{
            viewHolder.name.setText("正常报告-电容器组");

        }
        TableResults tableResults=list.get(position);
        viewHolder.tvNmae.setText(tableResults.getZhanming()+"-"+
                tableResults.getYunxingbianhao()+"-"+
                tableResults.getShiyandanwei()+"-"+
                tableResults.getShiyanxingzhi()+"-"+
                Utils.FormatDateTime(tableResults.getTestTime())
        );

        return convertView;
    }

    public class ViewHolder {
        public TextView name;
        public TextView tvNmae;
    }
}