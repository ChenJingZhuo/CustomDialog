package com.cjz.customdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CommonDialog extends AlertDialog {

    private TextView titleTv;               //显示的标题
    private TextView messageTv;             //显示的消息
    private Button negtiveBn,positiveBn;    //确认和取消按钮
    public CommonDialog(Context context){
        super(context);
    }
    private String message;
    private String title;
    private String positive,negtive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        initView();         //初始化界面的控件
        initEvent();        //初始化界面的控件的的点击事件
    }

    //初始化界面控件
    private void initView() {
        negtiveBn=findViewById(R.id.negtive);
        positiveBn=findViewById(R.id.positive);
        titleTv=findViewById(R.id.title);
        messageTv=findViewById(R.id.message);
    }

    //初始化界面控件的显示数据
    private void refreshView(){
        //如果自定义了title和message的信息，则会在弹出对话框中显示
        if (!TextUtils.isEmpty(title)){
            titleTv.setText(title);     //设置标题控件的文本为自定义的title
            titleTv.setVisibility(View.VISIBLE);    //标题控件设置为显示状态
        }else {
            titleTv.setVisibility(View.GONE);       //标题控件设置为隐藏状态
        }
        if(!TextUtils.isEmpty(message)){
            messageTv.setText(message);             //设置消息控件的文本为自定义的message信息
        }
        //如果没有自定义按钮的文本，则默认显示“确定”和“取消”
        if (!TextUtils.isEmpty(positive)){
            positiveBn.setText(positive);           //设置消息控件的文本为自定义的文本信息
        }else {
            positiveBn.setText("确定");            //设置按钮文本信息为“确定”
        }
        if (!TextUtils.isEmpty(negtive)){
            negtiveBn.setText(negtive);
        }else {
            negtiveBn.setText("取消");
        }
    }

    //初始化界面的确定和取消监听器
    private void initEvent(){
        //设置“确定”按钮的点击事件的监听器
        positiveBn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (onClickBottomListener!=null){
                    onClickBottomListener.onPositiveClick();
                }
            }
        });
        //设置“取消”按钮的点击事件的监听器
        negtiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickBottomListener!=null){
                    onClickBottomListener.onNegtiveClick();
                }
            }
        });
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }
    public interface OnClickBottomListener{
        void onPositiveClick();     //实现“确定”按钮的点击事件的方法
        void onNegtiveClick();      //实现“取消”按钮的点击事件的方法
    }
    //设置“确定”“取消”按钮的回调
    public OnClickBottomListener onClickBottomListener;

    public CommonDialog setOnClickButtomListener(OnClickBottomListener onClickButtomListener){
        this.onClickBottomListener=onClickButtomListener;
        return this;
    }

    public CommonDialog setMessage(String message){
        this.message=message;
        return this;
    }

    public CommonDialog setTitle(String title){
        this.title=title;
        return this;
    }

    public CommonDialog setPositive(String positive){
        this.positive=positive;
        return this;
    }

    public CommonDialog setNegtive(String negtive){
        this.negtive=negtive;
        return this;
    }
}
