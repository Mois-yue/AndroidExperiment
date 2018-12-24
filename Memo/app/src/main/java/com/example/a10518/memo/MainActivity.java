package com.example.a10518.memo;
import com.example.a10518.memo.Time.DateFormatType;
import com.example.a10518.memo.database.MyDatabase;
import com.example.a10518.memo.database.Record;
import static com.example.a10518.memo.Time.MyFormat.*;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class MainActivity extends BaseActivity {
    private final static String TAG = "MainActivity";
    MyDatabase myDatabase;
    private ListView mListView;
    private Button createButton;
    private MyBaseAdapter myBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //初始化控件
    private void init(){
        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.createButton:
                        Intent intent = new Intent(MainActivity.this, EditActivity.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                        break;
                    default:
                        break;
                }
            }
        });
        mListView = findViewById(R.id.view);
        List<Record> recordList = new ArrayList<>();
        myDatabase = new MyDatabase(this);
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = db.query(MyDatabase.TABLE_NAME_RECORD,null,null,null,null,null,MyDatabase.NOTICE_TIME+","+MyDatabase.RECORD_TIME+"DEC");
        if (cursor.moveToFirst()){
            Record record;
            while (!cursor.isAfterLast()){
                record = new Record();
                record.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(myDatabase.RECORD_ID))));
                record.setTitleName(cursor.getString(cursor.getColumnIndex(myDatabase.RECORD_TITLE)));
                record.setTextBody(cursor.getString(cursor.getColumnIndex(myDatabase.RECORD_BODY)));;
                record.setCreateTime(cursor.getString(cursor.getColumnIndex(myDatabase.RECORD_TIME)));
                record.setNoticeTime(cursor.getString(cursor.getColumnIndex(myDatabase.NOTICE_TIME)));
                recordList.add(record);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        //创建一个Adapter的实例
        myBaseAdapter = new MyBaseAdapter(this,recordList,R.layout.show);
        mListView.setAdapter(myBaseAdapter);
        //设置监听
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,AmendActivity.class);
                Record record = (Record) mListView.getItemAtPosition(position);
                intent.putExtra(myDatabase.RECORD_TITLE,record.getTitleName().trim());
                intent.putExtra(myDatabase.RECORD_BODY,record.getTextBody().trim());
                intent.putExtra(myDatabase.RECORD_TIME,record.getCreateTime().trim());
                intent.putExtra(myDatabase.RECORD_ID,record.getId().toString().trim());
                if (record.getNoticeTime()!=null) {
                    intent.putExtra(myDatabase.NOTICE_TIME, record.getNoticeTime().trim());
                }
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Record record = (Record) mListView.getItemAtPosition(position);
                showDialog(record,position);
                return false;
            }
        });
    }







    void showDialog(final Record record,final int position){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("是否删除？");
        String textBody = record.getTextBody();
        dialog.setMessage(
                textBody.length()>150?textBody.substring(0,150)+"...":textBody);
        dialog.setPositiveButton("删除",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = myDatabase.getWritableDatabase();
                        db.delete(myDatabase.TABLE_NAME_RECORD,
                                myDatabase.RECORD_ID +"=?",
                                new String[]{String.valueOf(record.getId())});
                        db.close();
                        myBaseAdapter.removeItem(position);
                        mListView.post(new Runnable() {
                            @Override
                            public void run() {
                                myBaseAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialog.show();
    }




    /**
     * ListView展示的适配器类
     */
    class MyBaseAdapter extends BaseAdapter{
        private List<Record> recordList;//数据集合
        private Context context;
        private int layoutId;

        public MyBaseAdapter(Context context,List<Record> recordList,int layoutId){
            this.context = context;
            this.recordList = recordList;
            this.layoutId = layoutId;
        }

        @Override
        public int getCount() {
            if (recordList!=null&&recordList.size()>0)
                return recordList.size();
            else
                return 0;
        }

        @Override
        public Object getItem(int position) {
            if (recordList!=null&&recordList.size()>0)
                return recordList.get(position);
            else
                return null;
        }

        public void removeItem(int position){
            this.recordList.remove(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(
                        getApplicationContext()).inflate(R.layout.show, parent,
                        false);
                viewHolder  = new ViewHolder();
                viewHolder.titleView = convertView.findViewById(R.id.item_title);
                viewHolder.bodyView = convertView.findViewById(R.id.item_body);
                viewHolder.timeView = convertView.findViewById(R.id.item_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Record record = recordList.get(position);
            String tile = record.getTitleName();
            viewHolder.titleView.setText((position+1)+"."+(tile.length()>7?tile.substring(0,7)+"...":tile));

            String body = record.getTextBody();
            viewHolder.bodyView.setText(body.length()>13?body.substring(0,12)+"...":body);

            String createTime = record.getCreateTime();
            Date date = myDateFormat(createTime, DateFormatType.NORMAL_TIME);
            viewHolder.timeView.setText(getTimeStr(date));
            return convertView;
        }
    }

    /**
     * ListView里的组件包装类
     */
    class ViewHolder{
        TextView titleView;
        TextView bodyView;
        TextView timeView;
    }

}
