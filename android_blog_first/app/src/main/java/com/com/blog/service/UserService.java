package com.com.blog.service;

import com.com.blog.model.User;
import com.com.blog.service.dto.CMRespDto;
import com.com.blog.service.dto.LoginDto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("/login")
    Call<CMRespDto<User>> login(@Body LoginDto loginDto);

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            // 집
            //.baseUrl("http://192.168.0.5:8080")
            // 학원
            .baseUrl("http://10.100.202.32:8080")
            .build();

    UserService service = retrofit.create(UserService.class);
}
