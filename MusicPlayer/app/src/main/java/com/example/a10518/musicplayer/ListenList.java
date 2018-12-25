package com.example.a10518.musicplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class ListenList extends AppCompatActivity implements View.OnClickListener {
    ImageButton play;
    ImageButton next;
    ImageButton last;
    ImageButton pause;
    private List<MusicList> list;
    private static int currentPosition = -1; //位置
    int totalNum;//歌曲的数量


    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Button bList = (Button) findViewById(R.id.list);
//        bList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(ListenList.this, MusicList.class);
//                startActivity(i);
//            }
//        });

        play = (ImageButton) findViewById(R.id.play);
        play.setOnClickListener(this);

        next = (ImageButton) findViewById(R.id.next);
        next.setOnClickListener(this);

        last = (ImageButton) findViewById(R.id.last);
        last.setOnClickListener(this);

        pause = (ImageButton) findViewById(R.id.pause);
        pause.setOnClickListener(this);

        list = (List<MusicList>)getIntent().getSerializableExtra("list");

        if(ContextCompat.checkSelfPermission(ListenList.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ListenList.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE },1);
        }
        else{
            play();  //初始化MediaPlayer
        }
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    play();
                }
                else{
                    Toast.makeText(this, "拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

            default:
        }
    }

    //播放
    private void play(){
        try{
            File file = new File(Environment.getExternalStorageDirectory(),".mp3");
            //指定音频文件的路径
            mediaPlayer.setDataSource(file.getPath());
            //让MediaPlayer进入到准备状态
            mediaPlayer.prepare();
            //将进度设置到"音乐进度"
            //mediaPlayer.seekTo(pausePosition);


            //播放开始
            mediaPlayer.start();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //暂停
    private void pause(){
        //直接调用MediaPlay 中的暂停方法
        mediaPlayer.pause();
        //获取暂停位置
        //pausePosition = play.getCurrentPosition();
    }

    //播放上一曲
    private void last(){
        //判断是否为第一首歌曲
        if(currentPosition > 0){
            currentPosition -= 1;
            play();
        }
        else {
            pause();
        }
    }
    //播放下一曲
    private void next(){
        totalNum = list.size();
        //如果是最后一首，则播放第一首
        if(currentPosition == totalNum -1){
            currentPosition = 0;
        }
        else {
            currentPosition += 1;
        }
        play();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //播放暂停按钮执行的方法
            case R.id.pause:
                if (mediaPlayer.isPlaying()){
                    //暂停
                    pause();
                }
                break;
            case R.id.play:
                play();
                break;
            case R.id.next:
                next();
                break;
            case R.id.last:
                last();
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }


}
