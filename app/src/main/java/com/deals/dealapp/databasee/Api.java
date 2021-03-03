package com.deals.dealapp.databasee;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    // User Register
    @FormUrlEncoded
    @POST("user_registration")
    Call<ResponseBody> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("remember_token") String remember_token


    );
//CategoryList

    @GET("getcategories")
    Call<ResponseBody> getcategories(

    );
}


