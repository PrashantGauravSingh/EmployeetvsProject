package com.andtechno.singh.psheet;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("username")
    public String userName = "test";
    @SerializedName("password")
    public String password = "123456";
}
