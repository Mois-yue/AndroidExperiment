package com.example.yiwei.wordlist.cn.edu.bistu.cs.se.words;
import android.app.Application;
import android.content.Context;

public class WordsApplication extends Application{
    private static Context context;
    public static Context getContext(){
        return WordsApplication.context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        WordsApplication.context=getApplicationContext();
    }
}