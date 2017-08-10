package cn.xxjc.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.xxjc.com.R;
import cn.xxjc.com.adapter.CollectData2Adapter;
import cn.xxjc.com.adapter.CollectDataAdapter;
import cn.xxjc.com.app.App;
import cn.xxjc.com.bean.TableCols;
import cn.xxjc.com.bean.TableResults;
import cn.xxjc.com.bean.Tables;
import cn.xxjc.com.config.DfhePreference;
import cn.xxjc.com.utils.Utils;
import cn.xxjc.com.utils.XmlParseUtil;
import cn.xxjc.com.view.InputDialog;
import cn.xxjc.com.view.TipDialog;
import cn.xxjc.com.view.TitleBarView;
import cn.xxjc.com.view.ToastManager;
import cn.xxjc.com.zxing.CaptureActivity;
import cn.xxjc.com.zxing.Intents;

public class CollectDatactivity extends FragmentActivity implements TitleBarView.OnTitleBarClickListener, View.OnClickListener {
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
    public static int index;
    private static final int REQUEST_CODE_GENERAL_WEBIMAGE = 108;
    InputDialog inputDialog;
    @Bind(R.id.title_detail)
    TextView titleDetail;

    String zhanming;
    String bianhao;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_datactivity);
        ButterKnife.bind(this);
        tableId = getIntent().getIntExtra("tableId", 0);
        tableName = getIntent().getStringExtra("tableName");

        title.withTitle("待采数据", 0).withLeftImage(R.mipmap.ic_back).setOnTitleBarClickListener(this);

        final ArrayList<TableCols> tableColses = Utils.getTableColsInTable(tableId);
        for (TableCols tableCol : tableColses) {
            tableCol.value = -1111;
        }

        totalData.addAll(tableColses);
        adapter = new CollectData2Adapter(this, totalData);
        listview.setAdapter(adapter);


        adapter.onSeeClickBack = new CollectDataAdapter.OnSeeClickBack() {
            @Override
            public void onClickBack(int position) {
                index = position;
                if (totalData.get(position).id % 10 == 2) {
                    Intent intent = new Intent(CollectDatactivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_GENERAL_WEBIMAGE);
                }
            }
        };

        tvBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);

        inputDialog = new InputDialog(this);
        inputDialog.setNegetiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDialog.cancel();
                finish();
            }
        });
        inputDialog.setPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date=new Date();
                titleDetail.setText("并联电容电气试验报告（共体）\n"+zhanming+"-"+bianhao+"-"
                        +InputDialog.strDanwei+"-"+InputDialog.strXingzhi+"-"+Utils.FormatDateTime(date ));
            }
        });
        inputDialog.show();

        if(BaoBiaoActivity.info!=null){
            String[]info=BaoBiaoActivity.info;
            String temp="";
            for(int i=0;i<info.length;i++){
                if(info[i].contains("站名")){
                    if(info[i].contains("："))
                        temp="：";
                    else
                        temp=":";
                    zhanming=info[i].substring(info[i].indexOf(temp)+1);
                }else if(info[i].contains("运行编号")){
                    if(info[i].contains("："))
                        temp="：";
                    else
                        temp=":";
                    bianhao=info[i].substring(info[i].indexOf(temp)+1);
                }
            }
        }



    }

    @Override
    public void onTitleBarClick(int titleId) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_save:
                //判断是否所有采集项 都采集了
                List<TableCols> list = Utils.getTableColsInTable(tableId);
                int isfullCaiji = 1;
                for (TableCols tableCol : list) {
                    if (tableCol.value == -1111 && tableCol.id % 10 == 2) {
                        isfullCaiji = 0;
                        break;
                    }
                }
                Tables table = XmlParseUtil.tables.get(XmlParseUtil.tables.indexOf(new Tables(tableId)));
                table.state = isfullCaiji;

                if (0 == isfullCaiji) {
                    ToastManager.showShortToast("数据未全部采集");
                } else {
                    String info = "";
                    if (App.getDaoSession().getTableResultsDao().count()% 2 == 0) {
                        info = "采集数据合格";
                    } else {
                        info = "采集数据不合格";

                    }

                    final TipDialog dialog = new TipDialog(this);
                    dialog.setIsShowTitle(false);
                    dialog.setMessage(info);
                    dialog.setStrOk("确定");
                    dialog.setStrCancel("取消");
                    dialog.setPositiveButton(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            TableResults tableResults=new TableResults();
                            tableResults.setShiyandanwei(InputDialog.strDanwei);
                            tableResults.setShiyanxingzhi(InputDialog.strXingzhi);
                            tableResults.setZhanming(zhanming);
                            tableResults.setState((int)App.getDaoSession().getTableResultsDao().count()% 2==0?1:0);
                            tableResults.setYunxingbianhao(bianhao);
                            tableResults.setTestTime(date);

                            App.getDaoSession().getTableResultsDao().insert(tableResults);

                            setResult(RESULT_OK);
                            startActivity(new Intent(CollectDatactivity.this, HistoryActivity.class));
                            finish();
                        }
                    });
                    dialog.setNegativeButton(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GENERAL_WEBIMAGE && resultCode == RESULT_OK) {
            String dataInfo = data.getStringExtra("data");
            if (!TextUtils.isEmpty(dataInfo)) {
                Intent intent = new Intent(this, CameraActivity.class);
                intent.putExtra("data", dataInfo);
                startActivityForResult(intent, 109);
            } else {
                ToastManager.showShortToast("信息有误");
            }
        } else if (requestCode == 109 && resultCode == RESULT_OK) {
            totalData.get(index).value = Utils.getValueByTableColsId(totalData.get(index).id);
            adapter.notifyDataSetChanged();
        }
    }
}
