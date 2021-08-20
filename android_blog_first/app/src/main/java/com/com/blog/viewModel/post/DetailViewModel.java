package com.com.blog.viewModel.post;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.model.Post;
import com.com.blog.service.PostService;
import com.com.blog.service.dto.CMRespDto;
import com.com.blog.view.post.PostListActivity;

import java.util.List;

import retrofit2.Call;

public class DetailViewModel extends ViewModel {


    private final PostService postService = PostService.service;

    public Call<CMRespDto<Post>> findById(int postId){
        return postService.findById(postId);
    }


    public void updateForm(Context mContext){
        Intent intent = new Intent(
                mContext,
                PostListActivity.class
        );
        mContext.startActivity(intent);
    }



    //
    private MutableLiveData<Post> posts = new MutableLiveData<>();

    // 옵저버
    public MutableLiveData<Post> detailPost(){
        return posts;
    }
}
