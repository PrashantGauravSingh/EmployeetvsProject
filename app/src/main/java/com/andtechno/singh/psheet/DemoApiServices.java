package com.andtechno.singh.psheet;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DemoApiServices {

    @POST("gettabledata.php")
    Call<ResponseBody> getLoginData(@Body LoginBody body);
}
