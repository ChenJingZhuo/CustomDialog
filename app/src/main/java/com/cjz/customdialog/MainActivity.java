package com.cjz.customdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AlertDialog dialog;

    private String[]titles={"桌子","苹果","蛋糕","线衣","猕猴桃","围巾"};
    private  String[] prices={"1800元","10元/kg","300元","350元","10元/kg","280元"};
    private int[] icons={R.drawable.table,R.drawable.apple,R.drawable.cake,R.drawable.wireclothes,R.drawable.kiwifruit,R.drawable.scarf};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init();
        init2();
    }

    private void init2() {
        Button btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.listview2, null);//获取自定义布局
        final ListView lv=layout.findViewById(R.id.lv);//绑定layout视图里面的ListView布局id
        MyBaseAdapter adapter=new MyBaseAdapter();
        lv.setAdapter(adapter);//适配lv引用的实例
        builder.setView(layout);//设置AlertDialog的视图为layout

        Button pn,nn;
        pn=layout.findViewById(R.id.positive);
        nn=layout.findViewById(R.id.negtive);
        pn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        nn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog=builder.create();
        dialog.show();//显示对话框
    }

    /*private void init() {
        Button btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CommonDialog dialog = new CommonDialog(MainActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("您确定要删除信息吗？");
                dialog.setNegtive("取消");
                dialog.setPositive("确定");
                dialog.setOnClickButtomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegtiveClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }*/

    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                convertView= View.inflate(MainActivity.this,R.layout.list_item,null);
                viewHolder=new ViewHolder();
                viewHolder.title=convertView.findViewById(R.id.title);
                viewHolder.price=convertView.findViewById(R.id.price);
                viewHolder.iv=convertView.findViewById(R.id.iv);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.title.setText(titles[position]);
            viewHolder.price.setText(prices[position]);
            viewHolder.iv.setBackgroundResource(icons[position]);
            return convertView;
        }
    }

    class ViewHolder{
        TextView title;
        TextView price;
        ImageView iv;
    }
}
