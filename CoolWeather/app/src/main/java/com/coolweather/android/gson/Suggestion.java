package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 2018/12/13.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;


    public Sport sport;

    public class Comfort{
        @SerializedName("txt")
        public String info;
    }
    public class Sport{
        @SerializedName("txt")
        public String info;
    }

}
