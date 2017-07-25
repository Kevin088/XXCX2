package cn.xxjc.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.xxjc.com.MainActivity;
import cn.xxjc.com.R;
import cn.xxjc.com.bean.User;
import cn.xxjc.com.config.DfhePreference;
import cn.xxjc.com.utils.XmlParseUtil;
import cn.xxjc.com.view.TitleBarView;
import cn.xxjc.com.view.ToastManager;

public class LoginActivity extends FragmentActivity implements View.OnClickListener {

    @Bind(R.id.title_login)
    TitleBarView titleLogin;
    @Bind(R.id.tv_login_username)
    EditText tvLoginUsername;
    @Bind(R.id.layout_02)
    LinearLayout layout02;
    @Bind(R.id.tv_login_pwd)
    EditText tvLoginPwd;
    @Bind(R.id.layout_03)
    LinearLayout layout03;
    @Bind(R.id.btn_login)
    TextView btnLogin;
    @Bind(R.id.activity_login)
    RelativeLayout activityLogin;
    String username;
    String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        titleLogin.withTitle("登录",0);
        if(DfhePreference.getIsLogin()){
            startActivity(new Intent(this,BaoBiaoActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        boolean isRight=false;
        username=tvLoginUsername.getText().toString().trim();
        pwd=tvLoginPwd.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            ToastManager.showShortToast("用户名为空");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            ToastManager.showShortToast("密码为空");
            return;
        }
        for(User user : XmlParseUtil.users){
            if(username.equals(user.userName)&&pwd.equals(user.pwd)){
                isRight=true;
                DfhePreference.setIsLogin(true);
                DfhePreference.setUserId(user.id);
                startActivity(new Intent(this, BaoBiaoActivity.class));
                finish();
                return;
            }
        }
        if(!isRight)
            ToastManager.showShortToast("用户名或密码不对");
    }
}
