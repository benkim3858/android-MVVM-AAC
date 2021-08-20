package com.com.blog.view.post;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.model.Post;
import com.com.blog.service.dto.CMRespDto;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.viewModel.post.DetailViewModel;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostDetailActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "PostDetailActivity";
    private final PostDetailActivity mContext = PostDetailActivity.this;

    private MaterialButton btnDelete, btnUpdateForm;
    private TextView tvBox;

    private DetailViewModel detailViewModel;
    private int postId;
    private Post post;

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        init();
        initLr();
        initViewModel();
        initSetting();
    }

    @Override
    public void init() {
        detailViewModel = new DetailViewModel();
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdateForm = findViewById(R.id.btnUpdateForm);
        tvBox = findViewById(R.id.tvBox);
    }

    @Override
    public void initLr() {
        btnUpdateForm.setOnClickListener(view -> {

        });
    }

    @Override
    public void initViewModel() {
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.detailPost().observe(this,detail->{

        });

    }

    @Override
    public void initSetting() {
        onAppBarSettings(true, "Detail");

        Intent getIntent = getIntent();
        postId = getIntent.getIntExtra("postId",0);
        if(postId == 0) finish();
    }

    @Override
    public void initData() {
        detailViewModel.findById(postId).enqueue(new Callback<CMRespDto<Post>>() {
            @Override
            public void onResponse(Call<CMRespDto<Post>> call, Response<CMRespDto<Post>> response) {
                CMRespDto<Post> cm = response.body();
                if (cm.getCode() == 1) {
                    post = cm.getData();
                    tvBox.setText(""); // 초기화
                    tvBox.append("id : " + cm.getData().getId() + "\n");
                    tvBox.append("title : " + cm.getData().getTitle() + "\n");
                    tvBox.append("content : " + cm.getData().getContent() + "\n");
                    tvBox.append("username : " + cm.getData().getUser().getUsername() + "\n");
                    tvBox.append("created : " + cm.getData().getCreated() + "\n");
                }
            }
            @Override
            public void onFailure(Call<CMRespDto<Post>> call, Throwable t) {

            }
        });
    }
}