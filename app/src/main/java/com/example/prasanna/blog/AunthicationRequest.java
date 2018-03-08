package com.example.prasanna.blog;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prasanna on 7/3/18.
 */

public class AunthicationRequest {
    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;

    public AunthicationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
