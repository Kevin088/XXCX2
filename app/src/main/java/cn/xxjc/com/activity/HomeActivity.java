package cn.xxjc.com.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.xxjc.com.R;
import cn.xxjc.com.adapter.Home2Adapter;
import cn.xxjc.com.adapter.HomeAdapter;
import cn.xxjc.com.bean.ErWeiMa1Bean;
import cn.xxjc.com.config.DfhePreference;
import cn.xxjc.com.utils.FileUtil;
import cn.xxjc.com.utils.GsonUtils;
import cn.xxjc.com.view.TitleBarView;
import cn.xxjc.com.view.ToastManager;
import cn.xxjc.com.zxing.CaptureActivity;

public class HomeActivity extends FragmentActivity implements TitleBarView.OnTitleBarClickListener, AdapterView.OnItemClickListener {

    @Bind(R.id.title)
    TitleBarView title;
    @Bind(R.id.listivew)
    ListView listivew;
    @Bind(R.id.activity_bao_biao)
    LinearLayout activityBaoBiao;
    ArrayList<Integer>totalData=new ArrayList<Integer>();
    Home2Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        title.withTitle("采集报表",0).withLeftImage(R.mipmap.ic_back).withRightImage(R.mipmap.ic_erweima).setOnTitleBarClickListener(this);


        int count= DfhePreference.getSaveCount();

        for(int i=1;i<=count;i++){
            totalData.add(i);
        }

        adapter=new Home2Adapter(this,totalData);
        listivew.setAdapter(adapter);
        listivew.setOnItemClickListener(this);


    }

    @Override
    public void onTitleBarClick(int titleId) {
        switch(titleId){
            case TitleBarView.TITLE_BAR_LEFT_CLICK:
                finish();
                break;
            case TitleBarView.TITLE_BAR_RIGHT_CLICK:
                startActivityForResult(new Intent(this, CaptureActivity.class),300);
                break;
            default:
                break;
        }
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

    @Override
    protected void onRestart() {
        super.onRestart();
        int count= DfhePreference.getSaveCount();
        totalData.clear();
        for(int i=1;i<=count;i++){
            totalData.add(i);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=null;

        if(i%2==0){
            //正确的
            intent=getWordFileIntent(new File(FileUtil.xmlPath,FileUtil.result_txt_R));
        }else{
            intent=getWordFileIntent(new File(FileUtil.xmlPath,FileUtil.result_txt_E));
        }
        try{
            startActivity(intent);
        }catch (Exception o){
            ToastManager.showShortToast("请安装WPS应用");
        }

    }
    //android获取一个用于打开Word文件的intent
    public  Intent getWordFileIntent( File param )
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(param );
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }
}
