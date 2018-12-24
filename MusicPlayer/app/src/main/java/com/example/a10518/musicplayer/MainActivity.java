package com.example.a10518.musicplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton play;
    private ImageButton next;
    private ImageButton last;
    private ImageButton pause;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        play.setOnClickListener(this);

        next = findViewById(R.id.next);
        next.setOnClickListener(this);

        last = findViewById(R.id.last);
        last.setOnClickListener(this);

        pause = findViewById(R.id.pause);
        pause.setOnClickListener(this);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE },1);
        }
        else{
            initMediaPlayer();  //初始化MediaPlayer
        }
    }

    private void initMediaPlayer(){
        try{
            File file = new File(Environment.getExternalStorageDirectory(),".mp3");
            mediaPlayer.setDataSource(file.getPath());//指定音频文件的路径
            mediaPlayer.prepare();//让MediaPlayer进入到准备状态
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }
                else{
                    Toast.makeText(this, "拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

            default:
        }
    }
    @Override
    public void Onclick(View v){

    }
}
