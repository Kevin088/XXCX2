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

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/13.
 */
public class Home2Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Integer> list;


    public Home2Adapter(Context context, List<Integer> list) {
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
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.name.setText(list.get(position)%2==1?"正常报告-电容器组":"异常报告-电容器组");
        return convertView;
    }

    public class ViewHolder {
        public TextView name;
    }
}