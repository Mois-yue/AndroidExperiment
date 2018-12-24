package com.example.a10518.musicplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


//歌曲或歌手名字过长则滚动显示，形成跑马灯的效果
public class ScrollDisplay extends TextView{
    public ScrollDisplay(Context context){
        super(context);
    }

    public ScrollDisplay(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public ScrollDisplay(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    //当列表被选中时，实现跑马灯的效果
    public boolean isfocused(){
        return true;
    }
}
