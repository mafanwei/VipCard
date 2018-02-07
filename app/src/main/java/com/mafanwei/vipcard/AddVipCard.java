package com.mafanwei.vipcard;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mafanwei on 2018/2/6.
 */

public class AddVipCard extends AppCompatActivity {
    private EditText mName,mContent;
    private Button mButton;
    private SQLiteDatabase db;
    private TextView mTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addvipcard);
        initview();
        addListener();
    }



    private void initview() {
        mName=(EditText) findViewById(R.id.add_name);
        mContent=(EditText) findViewById(R.id.add_content);
        mButton=(Button) findViewById(R.id.add_btn);
        mTextView=(TextView) findViewById(R.id.add_forget);
        db=DbManger.getSQLiteDatabase(this);
    }

    private void addListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mName.getText().toString().equals("")&&!mContent.getText().toString().equals(""))
                {
                    SimpleDateFormat    formatter    =   new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
                    Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
                    String    str    =    formatter.format(curDate);
                    ContentValues values=new ContentValues();
                    values.put("name",mName.getText().toString());
                    values.put("content",mContent.getText().toString());
                    values.put("uptime",str);
                    DbManger.saveVipCard(db,values);
                    Toast.makeText(AddVipCard.this,"新增成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(AddVipCard.this,"信息需要填全",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AddVipCard.this, CaptureActivity.class), 0);
               /* Intent intent = new Intent();
                intent.setClass(AddVipCard.this, Capure.class);
                startActivity(intent);*/
               // finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            mContent.setText(result);
        }
    }
}
