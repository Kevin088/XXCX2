package cn.xxjc.com.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.xxjc.com.R;
import cn.xxjc.com.utils.Utils;


/**
 * 自定义的AlertDialog
 * @author yc
 *
 * 2014-4-10 下午5:52:25
 */
public class InputDialog extends Dialog {
	private Button btnOk;
	private Button btnCancel;
	private View.OnClickListener PositiveButton,
			NegativeButton;
	private Context context;
 	public InputDialog(Context context) {
		super(context, R.style.tip_dialog);
		this.context=context;
	}
	public static String strXingzhi;
	public static String strTianqi;
	public static String strWendu;
	public static String strShidu;
	public static String strPeople;

	RadioGroup radioGroup;
	EditText edTianqi;
	EditText edWendu;
	EditText edShidu;
	EditText edPeople;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_dialog);
		RelativeLayout relative = (RelativeLayout) findViewById(R.id.TipDialog);
			relative.getLayoutParams().width = Utils.getScreenWidth(context);
		relative.getLayoutParams().height =Utils.getScreenHeight(context);

		radioGroup=(RadioGroup)findViewById(R.id.rg);
		edTianqi=(EditText) findViewById(R.id.et_dialog_weather);
		edWendu=(EditText) findViewById(R.id.et_dialog_wendu);
		edShidu=(EditText) findViewById(R.id.et_dialog_shidu);
		edPeople=(EditText) findViewById(R.id.et_dialog_people);


		btnCancel = (Button) findViewById(R.id.TwoDialogCancel);
		btnCancel.setOnClickListener(NegativeButton);

		btnOk = (Button) findViewById(R.id.TwoDialogOk);
		btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				strTianqi=edTianqi.getText().toString().trim();
				strWendu=edWendu.getText().toString().trim();
				strShidu=edShidu.getText().toString().trim();
				strPeople=edPeople.getText().toString().trim();

				;
				RadioButton rb = (RadioButton)InputDialog.this.findViewById(radioGroup.getCheckedRadioButtonId());
				//更新文本内容，以符合选中项
				strXingzhi=rb.getText().toString();

				if(TextUtils.isEmpty(strTianqi)||TextUtils.isEmpty(strWendu)
						||TextUtils.isEmpty(strShidu)||TextUtils.isEmpty(strPeople)
						||TextUtils.isEmpty(strXingzhi)){
					ToastManager.showShortToast("请完善信息");
				}else{
					InputDialog.this.cancel();
				}


			}
		});
	}

	public void  setNegetiveListener(View.OnClickListener listener){
		this.NegativeButton=listener;
	}

	


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
		}
		return false;
	}
}
