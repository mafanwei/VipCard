package com.mafanwei.vipcard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Mafanwei on 2018/2/6.
 */

public class ChangeCard extends AppCompatActivity {
    private TextView mTextView;
    private EditText mName,mContent;
    private Button mButton;
    private SQLiteDatabase db;
    private String _id;
    private Map<String,Object> map_item;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addvipcard);
        initView();
        initData();
        addListener();
    }

    private void initView() {
        mTextView=(TextView) findViewById(R.id.add_title);
        mName=(EditText) findViewById(R.id.add_name);
        mContent=(EditText) findViewById(R.id.add_content);
        mButton=(Button) findViewById(R.id.add_btn);
        db=DbManger.getSQLiteDatabase(this);
        Intent intent=getIntent();
        _id=intent.getStringExtra("_id");
    }

    private void initData() {
        map_item=DbManger.getVipCardById(db,_id);
        mTextView.setText("请修改下面的数据");
        mName.setText(map_item.get("name")+"");
        mContent.setText(map_item.get("content")+"");
    }

    private void addListener() {

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String sname="",scontent="";
                 sname=mName.getText().toString();
                 scontent=mContent.getText().toString();

                if(!sname.equals("")&&!scontent.equals("")){

                    if(!sname.equals(map_item.get("name")+"")||!scontent.equals(map_item.get("content")+""))
                    {
                        DbManger.deleteVipCard(db,map_item.get("_id")+"");

                        SimpleDateFormat formatter    =   new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
                        Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
                        String    str    =    formatter.format(curDate);
                        ContentValues values=new ContentValues();
                        values.put("name",mName.getText().toString());
                        values.put("content",mContent.getText().toString());
                        values.put("uptime",str);
                        DbManger.saveVipCard(db,values);
                        Toast.makeText(ChangeCard.this,"修改成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    else {
                        Toast.makeText(ChangeCard.this,"请至少修改一项",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(ChangeCard.this,"信息需要填全",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
