package cn.xxjc.com.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

import cn.xxjc.com.R;
import cn.xxjc.com.bean.TableCols;

public class CollectData2Adapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<TableCols> list;
        Context context;

        public CollectData2Adapter(Context context, List<TableCols> list) {
            inflater = LayoutInflater.from(context);
            this.list=list;
            this.context=context;
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
            if(true){
                convertView = inflater.inflate(R.layout.item_collect_data,
                        parent, false);
                viewHolder=new ViewHolder();
                viewHolder.name= (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.value= (EditText) convertView.findViewById(R.id.tv_data);
                viewHolder.imageView= (RelativeLayout) convertView.findViewById(R.id.rl_see);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText(list.get(position).tColName);
            if(list.get(position).value!=-1111){
                viewHolder.value.setText(list.get(position).value+"");
                viewHolder.name.setTextColor(ContextCompat.getColor(context,R.color.red));
            }else{

            }

            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(onSeeClickBack!=null){
                       onSeeClickBack.onClickBack(position);
                   }
                }
            });

            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.value.addTextChangedListener(new TextWatcher() {
                EditText editText= finalViewHolder.value;
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int value=0;
                    try {
                        value= Integer.parseInt(editText.getText().toString().trim());
                    }catch (Exception e){

                    }
                    list.get(position).value=value;
                    Log.e("ssssss",editText.getText()+"    =======");
                }
            });

            return convertView;
        }

        public class ViewHolder {
            public TextView name;
            public EditText value;
            public RelativeLayout imageView;
        }

    public interface OnSeeClickBack{
        void onClickBack(int position);
    }
    public CollectDataAdapter.OnSeeClickBack onSeeClickBack;
    }