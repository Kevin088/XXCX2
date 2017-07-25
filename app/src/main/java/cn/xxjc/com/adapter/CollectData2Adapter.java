package cn.xxjc.com.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import cn.xxjc.com.R;
import cn.xxjc.com.bean.TableCols;

public class CollectData2Adapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<TableCols> list;


        public CollectData2Adapter(Context context, List<TableCols> list) {
            inflater = LayoutInflater.from(context);
            this.list=list;
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
            if(convertView==null){
                convertView = inflater.inflate(R.layout.item_collect_data,
                        parent, false);
                viewHolder=new ViewHolder();
                viewHolder.name= (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.value= (EditText) convertView.findViewById(R.id.tv_data);
                viewHolder.imageView= (ImageView) convertView.findViewById(R.id.iv_see);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            if(list.get(position).type==1){
                viewHolder.value.setEnabled(true);
                viewHolder.value.setFocusable(true);
            }else if(list.get(position).type==2){
                viewHolder.value.setEnabled(false);
                viewHolder.value.setFocusable(false);
            }

            viewHolder.name.setText(list.get(position).tColName);
            viewHolder.value.setText(list.get(position).value+"");
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
                    double value=0;
                    try {
                        value= Double.parseDouble(editText.getText().toString().trim());
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
            public ImageView imageView;
        }

    public interface OnSeeClickBack{
        void onClickBack(int position);
    }
    public CollectDataAdapter.OnSeeClickBack onSeeClickBack;
    }