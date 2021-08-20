package com.com.blog.viewModel.post;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.model.Post;
import com.com.blog.service.PostService;
import com.com.blog.service.dto.CMRespDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    private static final String TAG = "ListViewModel";
    private final PostService postService = PostService.service;

    public void findAll() {
        postService.findAll().enqueue(new Callback<CMRespDto<List<Post>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Post>>> call, Response<CMRespDto<List<Post>>> response) {
                Log.d(TAG, "onResponse: "+response.body().getData().get(0).getTitle());
                posts.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Post>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    // MutableLiveData
    private MutableLiveData<List<Post>> posts = new MutableLiveData<>();


    // 옵저버 관찰
    public MutableLiveData<List<Post>> getPost() {
        return posts;
    }


    // post list 데이터
    public void initPost(){

        postService.initPost().enqueue(new Callback<CMRespDto<List<Post>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Post>>> call, Response<CMRespDto<List<Post>>> response) {
                CMRespDto<List<Post>> cm = response.body();
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Post>>> call, Throwable t) {

            }
        });


    }
}
