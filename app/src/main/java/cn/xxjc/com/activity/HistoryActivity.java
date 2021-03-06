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
import cn.xxjc.com.app.App;
import cn.xxjc.com.bean.ErWeiMa1Bean;
import cn.xxjc.com.bean.TableResults;
import cn.xxjc.com.config.DfhePreference;
import cn.xxjc.com.utils.FileUtil;
import cn.xxjc.com.utils.GsonUtils;
import cn.xxjc.com.view.TitleBarView;
import cn.xxjc.com.view.ToastManager;
import cn.xxjc.com.zxing.CaptureActivity;

public class HistoryActivity extends FragmentActivity implements TitleBarView.OnTitleBarClickListener, AdapterView.OnItemClickListener {

    @Bind(R.id.title)
    TitleBarView title;
    @Bind(R.id.listivew)
    ListView listivew;
    @Bind(R.id.activity_bao_biao)
    LinearLayout activityBaoBiao;
    ArrayList<TableResults>totalData=new ArrayList<TableResults>();
    Home2Adapter adapter;
    String position1="110kV大桥变电站-#1电容器组-电气试验班-例行-2014-03-20 10:15:00";
    String position2="110kV大桥变电站-#1电容器组-电气试验班-例行-2016-04-23 15:30:00";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        title.withTitle("采集报表",0).withLeftImage(R.mipmap.ic_back).setOnTitleBarClickListener(this);
        TableResults tableResults1=new TableResults();
        tableResults1.setOtherInfo(position1);
        TableResults tableResults2=new TableResults();
        tableResults2.setOtherInfo(position2);
        totalData.add(tableResults1);
        totalData.add(tableResults2);
        totalData.addAll(App.getDaoSession().getTableResultsDao().loadAll());

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

                break;
            default:
                break;
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=null;
        if(i==0){
            intent=getWordFileIntent(new File(FileUtil.xmlPath,FileUtil.result_txt_001));
        }else if(i==1){
            intent=getWordFileIntent(new File(FileUtil.xmlPath,FileUtil.result_txt_002));
        }else if(i%2==0){
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
