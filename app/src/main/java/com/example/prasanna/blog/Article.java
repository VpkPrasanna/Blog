package com.example.prasanna.blog;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prasanna on 8/3/18.
 */

public class Article {
    @SerializedName("id")
    Integer id;

    @SerializedName("title")
    String title;



    @SerializedName("heading")
    String heading;

    @SerializedName("date")
    SerializedName date;

    @SerializedName("context")
    String context;

    public Integer getId(){
        return getId();
    }

    public String getHeading() {
        return heading;
    }

    public String getTitle() {
        return title;
    }

    public SerializedName getDate() {
        return date;
    }

    public String getContext() {
        return context;
    }
}
