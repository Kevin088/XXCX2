package cn.xxjc.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xxjc.com.R;
import cn.xxjc.com.bean.ErWeiMa1Bean;
import cn.xxjc.com.utils.GsonUtils;
import cn.xxjc.com.view.ToastManager;
import cn.xxjc.com.zxing.CaptureActivity;

public class MainBeforeActivity extends FragmentActivity {

    @Bind(R.id.create_tables)
    Button createTables;
    @Bind(R.id.history_tables)
    Button historyTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_before);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.create_tables)
    public void click1(){
        startActivityForResult(new Intent(this, CaptureActivity.class),300);
    }

    @OnClick(R.id.history_tables)
    public void click2(){
        startActivity(new Intent(this, HistoryActivity.class));
    }
    ErWeiMa1Bean erWeiMa1Bean;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==300&&resultCode==RESULT_OK){
            String dataInfo=data.getStringExtra("data");

            erWeiMa1Bean=null;
            erWeiMa1Bean= GsonUtils.fromJson(dataInfo,ErWeiMa1Bean.class);
            if(erWeiMa1Bean!=null&&erWeiMa1Bean.tableid==1){
                Intent intent=new Intent(this,BaoBiaoActivity.class);
                intent.putExtra("data",dataInfo);
                startActivity(intent);
            }else{
                ToastManager.showShortToast("信息有误");
            }
        }

    }


}
