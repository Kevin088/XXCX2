package cn.xxjc.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.xxjc.com.R;
import cn.xxjc.com.adapter.CollectData2Adapter;
import cn.xxjc.com.adapter.CollectDataAdapter;
import cn.xxjc.com.bean.TableCols;
import cn.xxjc.com.bean.TableColsValue;
import cn.xxjc.com.bean.Tables;
import cn.xxjc.com.utils.FileUtil;
import cn.xxjc.com.utils.Utils;
import cn.xxjc.com.utils.XmlParseUtil;
import cn.xxjc.com.view.TitleBarView;
import cn.xxjc.com.zxing.CaptureFalseActivity;

public class CollectDatactivity extends FragmentActivity implements TitleBarView.OnTitleBarClickListener, View.OnClickListener {

    //CollectDataAdapter adapter;
    CollectData2Adapter adapter;
    ArrayList<TableCols> totalData = new ArrayList<>();
    int tableId;
    String tableName;
    @Bind(R.id.title)
    TitleBarView title;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.line1)
    View line1;
    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.layout_02)
    RelativeLayout layout02;
     int index;
    private static final int REQUEST_CODE_GENERAL_WEBIMAGE = 108;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_datactivity);
        ButterKnife.bind(this);
        tableId = getIntent().getIntExtra("tableId", 0);
        tableName=getIntent().getStringExtra("tableName");

        title.withTitle("待采数据", 0).withLeftImage(R.mipmap.ic_back).setOnTitleBarClickListener(this);

        ArrayList<TableCols> tableColses = Utils.getTableColsInTable(tableId,1);//1 , 0显示 不显示
        for (TableCols tableCol : tableColses) {
            tableCol.value = Utils.getValueByTableColsId(tableCol.id);
        }

        totalData.addAll(tableColses);
        adapter = new CollectData2Adapter(this, totalData);
        listview.setAdapter(adapter);


        adapter.onSeeClickBack=new CollectDataAdapter.OnSeeClickBack() {
            @Override
            public void onClickBack(int position) {
//                totalData.get(position).value++;
//                adapter.notifyDataSetChanged();


                index=position;
                Intent intent = new Intent(CollectDatactivity.this, CaptureFalseActivity.class);

                startActivityForResult(intent, REQUEST_CODE_GENERAL_WEBIMAGE);


            }
        };

        tvBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

    @Override
    public void onTitleBarClick(int titleId) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_save:
                //判断是否所有采集项 都采集了
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<TableCols> list= Utils.getTableColsInTable(tableId,1);
                        int isfullCaiji=1;
                        for(TableCols tableCol:list){
                            if(tableCol.value==0){
                                isfullCaiji=0;
                                break;
                            }
                        }
                        Tables table=XmlParseUtil.tables.get(XmlParseUtil.tables.indexOf(new Tables(tableId)));
                        table.state=isfullCaiji;
                        try {
                            XmlParseUtil.tableColsValues.clear();
                            int index=1;
                            for(TableCols tableCol:XmlParseUtil.tableCols){
                                TableColsValue valueBean=new TableColsValue();
                                valueBean.id=index;
                                valueBean.colId=tableCol.id;
                                valueBean.colValue=tableCol.value;
                                valueBean.tableId=tableId;
                                valueBean.tablename=tableName;
                                XmlParseUtil.tableColsValues.add(valueBean);
                                index++;
                            }
                            XmlParseUtil.saveDataSheetXml();
                            XmlParseUtil.saveDefinitionXml();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_GENERAL_WEBIMAGE&&resultCode==RESULT_OK){
            if(index>=0&&index<totalData.size()){
                totalData.get(index).value=999;
                adapter.notifyDataSetChanged();
            }

        }

    }
}
