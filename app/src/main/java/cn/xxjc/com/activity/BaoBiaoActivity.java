package cn.xxjc.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.xxjc.com.R;
import cn.xxjc.com.adapter.BaobiaoAdapter;
import cn.xxjc.com.app.App;
import cn.xxjc.com.bean.ErWeiMa1Bean;
import cn.xxjc.com.bean.Tables;
import cn.xxjc.com.bean.User2Tables;
import cn.xxjc.com.config.DfhePreference;
import cn.xxjc.com.utils.GsonUtils;
import cn.xxjc.com.view.TitleBarView;
import cn.xxjc.com.view.ToastManager;
import cn.xxjc.com.zxing.CaptureActivity;

public class BaoBiaoActivity extends FragmentActivity implements TitleBarView.OnTitleBarClickListener, AdapterView.OnItemClickListener {

    @Bind(R.id.title)
    TitleBarView title;
    @Bind(R.id.listivew)
    ListView listivew;
    @Bind(R.id.activity_bao_biao)
    LinearLayout activityBaoBiao;
    BaobiaoAdapter adapter;
    ArrayList<Tables> totalData=new ArrayList<>();
    private Tables table;
    private TextView headViewText;
    ErWeiMa1Bean erWeiMa1Bean;
    public static String [] info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_biao);
        ButterKnife.bind(this);
        title.withTitle("被测设备信息",0).withLeftImage(R.mipmap.ic_back).setOnTitleBarClickListener(this);

        totalData.addAll(User2Tables.getUserTables(DfhePreference.getUserId()));
        adapter=new BaobiaoAdapter(this,totalData,R.layout.item_baobiao);
        listivew.setAdapter(adapter);
        listivew.setOnItemClickListener(this);
        headViewText=findViewById(R.id.tv_msg);

        String dataInfo=getIntent().getStringExtra("data");
        erWeiMa1Bean= GsonUtils.fromJson(dataInfo,ErWeiMa1Bean.class);
        info=erWeiMa1Bean.info.split(";");
        String info=erWeiMa1Bean.info.replace(";","\n");
        headViewText.setText(info);
    }
    @Override
    public void onTitleBarClick(int titleId) {
        switch(titleId){
            case TitleBarView.TITLE_BAR_LEFT_CLICK:
                btnBackClick();
            break;
            case TitleBarView.TITLE_BAR_RIGHT_CLICK:
               // startActivityForResult(new Intent(this, CaptureActivity.class),300);
                break;
            default:
            break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        App.clickCount+=1;
        table = totalData.get(i);
        Intent intent = new Intent(this, CollectDatactivity.class);
        intent.putExtra("tableId", table.id);
        intent.putExtra("tableName",totalData.get(i).tableName);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            btnBackClick();
        }
        return false;
    }
    public void  btnBackClick(){
        finish();
    }
}
