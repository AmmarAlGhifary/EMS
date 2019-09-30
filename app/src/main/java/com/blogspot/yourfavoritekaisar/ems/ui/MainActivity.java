package com.blogspot.yourfavoritekaisar.ems.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import com.blogspot.yourfavoritekaisar.ems.Forum.ForumActivity;
import com.blogspot.yourfavoritekaisar.ems.Forum.chatroom;
import com.blogspot.yourfavoritekaisar.ems.NewsInet.PagerNewsActivity;
import com.blogspot.yourfavoritekaisar.ems.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.imgList)
    ImageView imgList;
    @BindView(R.id.txtLandmark)
    TextView txtLandmark;
    @BindView(R.id.cvNews)
    CardView cvNews;
    @BindView(R.id.imgForum)
    ImageView imgForum;
    @BindView(R.id.txtForum)
    TextView txtForum;
    @BindView(R.id.cvForum)
    CardView cvForum;
    @BindView(R.id.imgEmployee)
    ImageView imgEmployee;
    @BindView(R.id.txtEmployee)
    TextView txtEmployee;
    @BindView(R.id.cvEmployee)
    CardView cvEmployee;
    @BindView(R.id.imgAttedance)
    ImageView imgAttedance;
    @BindView(R.id.txtAttedance)
    TextView txtAttedance;
    @BindView(R.id.ScrollView)
    NestedScrollView ScrollView;
    @BindView(R.id.cvAttedance)
    CardView cvAttedance;
    @BindView(R.id.imgOvertime)
    ImageView imgOvertime;
    @BindView(R.id.txtOvertime)
    TextView txtOvertime;
    @BindView(R.id.cvOvertime)
    CardView cvOvertime;
    @BindView(R.id.imgLeave)
    ImageView imgLeave;
    @BindView(R.id.Leave)
    TextView Leave;
    @BindView(R.id.cvLeave)
    CardView cvLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.cvNews, R.id.cvForum, R.id.cvEmployee, R.id.cvAttedance, R.id.cvOvertime, R.id.cvLeave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cvNews:
                Intent news = new Intent(MainActivity.this, PagerNewsActivity.class);
                startActivity(news);
                break;
            case R.id.cvForum:
                Intent forum = new Intent(MainActivity.this, ForumActivity.class);
                startActivity(forum);
                break;
            case R.id.cvEmployee:
                break;
            case R.id.cvAttedance:
                break;
            case R.id.cvOvertime:
                break;
            case R.id.cvLeave:
                break;
        }
    }
}
