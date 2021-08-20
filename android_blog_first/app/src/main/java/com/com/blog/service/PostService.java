package com.com.blog.service;

import com.com.blog.config.SessionInterceptor;
import com.com.blog.model.Post;
import com.com.blog.service.dto.CMRespDto;
import com.com.blog.service.dto.PostUpdateDto;
import com.com.blog.view.post.PostUpdateActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PostService {

    // 글 update (수정)
    Call<CMRespDto<Post>> update(@Path("id") int id, @Body PostUpdateDto postUpdateDto);

    // 글 삭제 하기
    @DELETE("/post/{id}")
    Call<CMRespDto> deleteById(@Path("id")int id);

    // findById
    Call<CMRespDto<Post>> findById(@Path("id") int id);

    // findAll
    @GET("/init/post")
    Call<CMRespDto<List<Post>>> findAll();

    // initPost
    @GET
    Call<CMRespDto<List<Post>>> initPost();

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new SessionInterceptor()).build();

    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            // 집
            //.baseUrl("http://192.168.0.5:8080")
            // 학원
            .baseUrl("http://10.100.202.32:8080")
            .build();

    PostService service = retrofit.create(PostService.class);
}
