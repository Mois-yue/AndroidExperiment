package com.example.a10518.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private ListView listenList;
    private SimpleAdapter mAdapter;
    List<MusicInfo> musicInfo = null;
    private List<HashMap<String, Object>> musicList;
    private HashMap<String , Object> map;




    protected void onCreat(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        listenList = (ListView) findViewById(R.id.musicList);

        //给ListView添加点击事件
        listenList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListenList.class);
                startActivity(i);
            }
        });

        //获取歌曲对象集合
        musicInfo = MusicList.getMusicInfo(getApplicationContext());
        //显示歌曲列表
        setListAdpter(MusicList.getMusicMaps(musicInfo));
    }

    public void setListAdpter(List<HashMap<String ,String >>musicList){
        mAdapter = new SimpleAdapter(this,musicList,R.layout.item,new String[]{"url","title","artist"},new int[]{R.id.item_url,R.id.item_title,R.id.item_artist});
        listenList.setAdapter(mAdapter);
    }


}
