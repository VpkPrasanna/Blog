package com.example.prasanna.blog;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prasanna on 7/3/18.
 */

public class ErrorResponse {
    @SerializedName("error")
    String error;

    public String getError() {
        return error;
    }
}
