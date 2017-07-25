package cn.xxjc.com.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * 通用的ViewHolder，如果ViewHolder == null，初始化时需要传入上下文、ViewGroup、布局文件id、position
 * 
 * @author yc
 * @2015-4-27
 * 
 */
public class ViewHolder {
	private SparseArray<View> mViews;
	private int mPosition;
	private View mCconvertView;
	private Context mContext;

	private ViewHolder(Context context, ViewGroup parent, int layoutId,
					   int position) {
		this.mContext = context;
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mCconvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mCconvertView.setTag(this);

	};

	/**
	 * 获取Holder 注意Holder的复用
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static ViewHolder getHolder(Context context, View convertView,
									   ViewGroup parent, int layoutId, int position) {
		if (null == convertView) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	/**
	 * 通过viewId获取控件
	 * 
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {

		View childView = mViews.get(viewId);
		if (null == childView) {
			childView = mCconvertView.findViewById(viewId);
			mViews.put(viewId, childView);
		}
		return (T) childView;
	}

	public int getPosition() {
		return mPosition;
	}

	public View getCconvertView() {
		return mCconvertView;
	}

	/**
	 * 设置TextView的值
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setTextViewText(int viewId, String text) {
		TextView tv = (TextView) getView(viewId);
		tv.setText(text);
		return this;
	}

	public ViewHolder setTextViewText(int viewId, String text, int foregroundColor, int start, int end){
		TextView tv = getView(viewId);
		SpannableStringBuilder strBuilder = new SpannableStringBuilder(text);
		strBuilder.setSpan(new ForegroundColorSpan(foregroundColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(strBuilder);
		return this;
	}

	public ViewHolder  setTextViewCompoundDrawables(int viewId, Drawable drawable){
		TextView tv = (TextView) getView(viewId);
		tv.setCompoundDrawables(drawable, null, null, null);
		return this;
	}
	/**
	 * 设置Button的值
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setButtonText(int viewId, String text) {
		Button btn = (Button) getView(viewId);
		btn.setText(text);
		return this;
	}

	/**
	 * 设置Image的值
	 * 
	 * @param viewId
	 * @param resId
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int resId) {
		ImageView iv = (ImageView) getView(viewId);
		iv.setImageResource(resId);
		return this;
	}

	/**
	 * 设置Bitmap的值
	 * 
	 * @param viewId
	 * @param
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView iv = (ImageView) getView(viewId);
		iv.setImageBitmap(bitmap);
		return this;
	}

	/**
	 * 根据图片url设置Image的值
	 * 
	 * @param viewId
	 * @param
	 * @return
	 */

	/**
	 * 设置ViewGroup的背景色
	 * 
	 * @param viewId
	 * @param colorId
	 * @return
	 */
	public ViewHolder setViewGroupBg(int viewId, int colorId) {
		ViewGroup vg = (ViewGroup) getView(viewId);
		vg.setBackgroundResource(colorId);
		return this;
	}
	/**
	 * 设置ViewGroup的背景色
	 *
	 * @param viewId
	 * @param color
	 * @return
	 */
	public ViewHolder setViewGroupBgColor(int viewId, int color) {
		ViewGroup vg = (ViewGroup) getView(viewId);
		vg.setBackgroundColor(color);
		return this;
	}

	/**
	 * 设置TextView的顶部图片
	 * 
	 * @param viewId
	 *            TextView的Id
	 * @param resId
	 *            图片资源Id
	 * @return
	 */
	public ViewHolder setTextViewTopDrawable(int viewId, int resId) {
		TextView textView = (TextView) getView(viewId);
		textView.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
		return this;
	}

	/**
	 * 设置TextView的文本颜色
	 * 
	 * @param viewId
	 * @param color
	 * @return
	 */
	public ViewHolder setTextViewColor(int viewId, int color) {
		TextView textView = (TextView) getView(viewId);
		textView.setTextColor(color);
		return this;
	}
	/**
	 * 设置TextView的文本颜色
	 * 
	 * @param viewId
	 * @param color
	 * @return
	 */
	public ViewHolder setTextViewColorRes(int viewId, int color) {
		TextView textView = (TextView) getView(viewId);
		textView.setTextColor(mContext.getResources().getColor(color));
		return this;
	}

	/**
	 * 设置控件的显示和隐藏
	 * 
	 * @param viewId
	 * @param visibility
	 * @return
	 */
	public ViewHolder setViewVisibility(int viewId, int visibility) {
		View view = getView(viewId);
		view.setVisibility(visibility);
		return this;
	}
	/**
	 * 设置EditText的内容
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setEditTextText(int viewId, String text){
		EditText editText = getView(viewId);
		editText.setText(text);
		return this;
	}
	/**
	 * 设置EditText的焦点变化监听
	 * @param viewId
	 * @param l
	 * @return
	 */
	public ViewHolder setEditTextOnFocusChangedListener(int viewId, OnFocusChangeListener l){
		EditText editText = getView(viewId);
		editText.setOnFocusChangeListener(l);
		return this;
	}
	/**
	 * 设置TextView的点击监听
	 * @param viewId
	 * @param l
	 * @return
	 */
	public ViewHolder setTextViewOnClickListener(int viewId, OnClickListener l){
		TextView textView = getView(viewId);
		textView.setOnClickListener(l);
		return this;
	}

	public ViewHolder setTextViewTypeface(int viewId, Typeface typeface){
		TextView textView = getView(viewId);
		textView.setTypeface(typeface);
		return this;
	}

	public ViewHolder setTextViewBackGroundRes(int viewId, int resId) {
		TextView textView = getView(viewId);
		textView.setBackgroundColor(mContext.getResources().getColor(resId));
		return this;
	}
	public ViewHolder setViewOnClickListener(int viewId, OnClickListener l){
		View view = getView(viewId);
		view.setOnClickListener(l);
		return this;
	}
	public ViewHolder setCheckBoxChecked(int viewId, boolean l){
		CheckBox view = getView(viewId);
		view.setChecked(l);
		return this;
	}
	public ViewHolder setCheckBoxClickable(int viewId, boolean l){
		CheckBox view = getView(viewId);
		view.setClickable(l);
		return this;
	}
	public ViewHolder setProgress(int viewId, int value){
		ProgressBar view = getView(viewId);
		view.setProgress(value);
		return this;
	}
}
