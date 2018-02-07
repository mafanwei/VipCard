package com.mafanwei.vipcard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.encoding.EncodingUtils;


/**
 * Created by Mafanwei on 2018/2/6.
 */

public class CardDetails extends AppCompatActivity {
    private ImageView mImageView1,mImageView2;
    private String name,content;
    private TextView mTitle,mText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailscard);
        initview();
        createonecode();
        createQRcode();
    }

    private void initview() {
        mImageView1=(ImageView) findViewById(R.id.des_one);
        mImageView2=(ImageView) findViewById(R.id.des_two);
        mTitle=(TextView) findViewById(R.id.des_name);
        mText=(TextView) findViewById(R.id.des_content);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        content=intent.getStringExtra("content");
        mText.setText(content);
        mTitle.setText(name);
    }

    private void createonecode() {
        Bitmap qrCodeBitmap = EncodingUtils.creatBarcode(this, content,500,40,false);
        if(qrCodeBitmap!=null) mImageView1.setImageBitmap(qrCodeBitmap);
        else Toast.makeText(this, "解析错误", Toast.LENGTH_SHORT).show();
        mImageView1.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void createQRcode() {
        Bitmap qrCodeBitmap = EncodingUtils.createQRCode(content, 300,300,null);
        if(qrCodeBitmap!=null) mImageView2.setImageBitmap(qrCodeBitmap);
        else Toast.makeText(this, "解析错误", Toast.LENGTH_SHORT).show();
        mImageView2.setScaleType(ImageView.ScaleType.FIT_XY);
    }


}
