package com.example.prasanna.blog;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prasanna on 7/3/18.
 */

public class MessageResponse {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
