package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 2018/12/13.
 */

public class Basic {
    //使用@SerializedName注解的方式让JSON字段和Java字段间建立映射关系
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }

}
