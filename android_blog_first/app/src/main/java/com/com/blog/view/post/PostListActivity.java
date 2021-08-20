package com.com.blog.view.post;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.blog.R;
import com.com.blog.controller.PostController;
import com.com.blog.model.Post;
import com.com.blog.service.dto.CMRespDto;
import com.com.blog.util.MyConvert;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.view.post.adapter.PostListAdapter;
import com.com.blog.viewModel.post.ListViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "PostListActivity";
    private PostListActivity mContext = PostListActivity.this;

    private RecyclerView rvPostList;
    private RecyclerView.LayoutManager rvLayoutManager;

    private PostListAdapter postListAdapter;
    private PostController postController;
    private ListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        init();
        initLr();
        initSetting();
        initAdapter();
        initViewModel();
        initData();

    }

    @Override
    public void init() {
        postController = new PostController();
        rvPostList = findViewById(R.id.rvPostList);
    }

    @Override
    public void initLr() {

    }

    @Override
    public void initSetting() {
        onAppBarSettings("Home");
    }

    @Override
    public void initAdapter() {
        rvLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        rvPostList.setLayoutManager(rvLayoutManager);

        postListAdapter = new PostListAdapter(mContext);
        rvPostList.setAdapter(postListAdapter);
    }

    @Override
    public void initData() {
        listViewModel.findAll();
    }

    @Override
    public void initViewModel() {
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        // 옵저버
        listViewModel.getPost().observe(this,changePost->{
            postListAdapter.addItems(listViewModel.getPost().getValue());
        });
    }
}