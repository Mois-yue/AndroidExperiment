package com.example.a10518.musicplayer;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//歌曲列表
public class MusicList {
    //扫描系统中的MP3文件，返回一个list集合
    public static List<MusicInfo> getMusicInfo(Context context){
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        List<MusicInfo> MusicList = new ArrayList<>();
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            MusicInfo music = new MusicInfo();
            music.id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            music.title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            music.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            music.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            music.url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            music.duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

            if (music.size > 1000*800){

                //将标题分离出歌曲名和歌手。
                if (music.url.contains("-")){
                    String[] string = music.url.split("-");
                    music.artist = string[0];
                    music.title = string[1];
                }
                MusicList.add(music);
            }
            //释放资源
            cursor.close();
        }
        return MusicList;
    }

    public static List<HashMap<String, String>> getMusicMaps(
            List<MusicInfo> musicInfo) {
        List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();

        int i=0;
        for (Iterator iterator = musicInfo.iterator(); iterator.hasNext();) {
            i++;
            MusicInfo musicInfos = (MusicInfo) iterator.next();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("number",String.valueOf(i));
            map.put("id",String.valueOf(musicInfos.getId()));
            map.put("title", musicInfos.getTitle());
            map.put("Artist", musicInfos.getArtist());
            map.put("duration", formatTime(musicInfos.getDuration()));
            map.put("size", String.valueOf(musicInfos.getSize()));
            map.put("url", musicInfos.getUrl());
            mp3list.add(map);
        }
        return mp3list;
    }



    //将获取的时间格式化：将毫秒转换成分：秒的格式
    public static String formatTime(long time){
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2){
            min = "0" + time/(1000*60) + "";
        }
        else {
            min = time/(1000*60) + "";
        }

        if(sec.length() == 4){
            sec = "0" + (time % (1000*60)) + "";
        }
        else if(sec.length() == 3){
            sec = "00" + (time % (1000*60)) + "";
        }
        else if (sec.length() == 2){
            sec = "000" + (time % (1000*60)) + "";
        }
        else if (sec.length() == 1){
            sec = "0000" + (time % (1000*60)) + "";
        }
        return min + ":" + sec.trim().substring(0,2);
    }

}
