package com.com.blog.viewModel.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.config.SessionUser;
import com.com.blog.model.User;
import com.com.blog.service.UserService;
import com.com.blog.service.dto.AuthDto;
import com.com.blog.service.dto.CMRespDto;
import com.com.blog.service.dto.LoginDto;
import com.com.blog.view.post.PostListActivity;

import javax.xml.transform.Result;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final UserService userService = UserService.service;
    private final AuthViewModel mContext = AuthViewModel.this;

    // 접근제어자 - 리턴 타입 - 함수 이름 - 인자값
    public void login(LoginDto loginDto){
       userService.login(loginDto).enqueue(new Callback<CMRespDto<User>>() {
           @Override
           public void onResponse(Call<CMRespDto<User>> call, Response<CMRespDto<User>> response) {
               CMRespDto<User> cm = response.body();
               Headers headers = response.headers();
               Log.d(TAG, "onResponse: "+response);
               Log.d(TAG, "onResponse: "+response.body());
               Log.d(TAG, "onResponse: "+ cm.getCode());
               Log.d(TAG, "onResponse: "+ cm.getData().getUsername());
               Log.d(TAG, "onResponse: 토큰" + headers.get("Authorization"));
               SessionUser.user = cm.getData();
               SessionUser.token = headers.get("Authorization");
               getLogin().setValue(cm);

           }

           @Override
           public void onFailure(Call<CMRespDto<User>> call, Throwable t) {
                t.printStackTrace();
           }
       });
    }

    // MutableLiveData
    private MutableLiveData<CMRespDto<User>> login = new MutableLiveData<>();

    // 옵저버
    public MutableLiveData<CMRespDto<User>> getLogin(){
        return login;
    }

}
