package com.mafanwei.vipcard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private ListView mListView;
    private List<Map<String,Object>> mList;
    private SQLiteDatabase db;
    private SimpleAdapter mSimpleAdapter;
    private View mEmptyview;
    private Intent mNewIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        initview();
        initdata();
        addListener();
    }

    private void initview() {
        mTextView=(TextView) findViewById(R.id.main_newcard);
        mListView=(ListView) findViewById(R.id.main_list);
        db=DbManger.getSQLiteDatabase(this);
        mEmptyview=findViewById(R.id.emptyview);
        mNewIntent=new Intent(MainActivity.this,AddVipCard.class);
    }

    private void initdata() {
        mList=DbManger.getVipCardAll(db);
        mSimpleAdapter=new SimpleAdapter(this,mList,android.R.layout.simple_list_item_2,new String[]{"name","uptime"},new int[]{android.R.id.text1,android.R.id.text2});
        mListView.setAdapter(mSimpleAdapter);
        mListView.setEmptyView(mEmptyview);
    }

    private void addListener() {
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mNewIntent);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,CardDetails.class);
                intent.putExtra("name",mList.get(position).get("name")+"");
                intent.putExtra("content",mList.get(position).get("content")+"");
                startActivity(intent);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

                builder.setPositiveButton("编辑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2=new Intent(MainActivity.this,ChangeCard.class);
                        intent2.putExtra("_id",mList.get(position).get("_id")+"");
                        startActivity(intent2);
                    }
                });

                builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DbManger.deleteVipCard(db,mList.get(position).get("_id")+"");
                        Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                        updateListView();
                    }
                });

                AlertDialog a=builder.create();
                a.setMessage("对 "+mList.get(position).get("name")+" 要做什么");
                a.show();

                return true;
            }
        });
    }

    private void updateListView() {
        mList.clear();
        mList.addAll(DbManger.getVipCardAll(db));
        mSimpleAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        updateListView();
        super.onResume();
    }
}
