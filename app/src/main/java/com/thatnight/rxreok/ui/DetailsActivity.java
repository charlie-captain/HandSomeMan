package com.thatnight.rxreok.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thatnight.rxreok.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailsActivity extends AppCompatActivity {

    @InjectView(R.id.iv_details)
    ImageView mIvDetails;
    @InjectView(R.id.tlb_details)
    Toolbar mTlbDetails;
    @InjectView(R.id.wv_details)
    WebView mWvDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        Bundle bundle=getIntent().getExtras();
        ArrayList<String> receiveData=bundle.getStringArrayList("data");
        mTlbDetails.setTitle("新闻详情");
        setSupportActionBar(mTlbDetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTlbDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mWvDetails.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWvDetails.loadUrl(receiveData.get(1));
        Glide.with(this)
                .load(receiveData.get(0))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mIvDetails);

    }

    @Override
    public void onBackPressed() {
        if(mWvDetails.canGoBack()){
            mWvDetails.goBack();
            return;
        }
        super.onBackPressed();
    }


    //防止WebView内存泄漏
    @Override
    protected void onDestroy() {
        if(mWvDetails!=null){
            mWvDetails.loadDataWithBaseURL(null,"","text/html","utf-8",null);
            mWvDetails.clearHistory();
            mWvDetails.destroy();
            mWvDetails=null;
        }
        super.onDestroy();
    }
}
