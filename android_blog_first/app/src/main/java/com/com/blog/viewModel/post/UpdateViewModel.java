package com.com.blog.viewModel.post;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.model.Post;
import com.com.blog.service.PostService;
import com.com.blog.service.dto.CMRespDto;
import com.com.blog.service.dto.PostUpdateDto;

import java.util.List;

import retrofit2.Call;

public class UpdateViewModel extends ViewModel {
    private final PostService postService = PostService.service;

    // 글 수정 페이지로 이동
    public Call<CMRespDto<Post>> update(int postId, PostUpdateDto postUpdateDto){
        return postService.update(postId,postUpdateDto);
    }

    // 데이터 수정
    public void updatePost(){

    }

    // 옵저버 관찰
//    public MutableLiveData<List<Post>> getPost() {
//        return posts;
//    }
}
