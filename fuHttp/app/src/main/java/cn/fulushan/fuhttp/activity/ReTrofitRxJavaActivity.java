package cn.fulushan.fuhttp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fulushan.fuhttp.R;
import cn.fulushan.fuhttp.bean.Article;
import cn.fulushan.fuhttp.config.Url;
import cn.fulushan.fuhttp.net.FuRestClient;
import cn.fulushan.fuhttp.net.callback.IError;
import cn.fulushan.fuhttp.net.callback.IFailure;
import cn.fulushan.fuhttp.net.callback.IRequest;
import cn.fulushan.fuhttp.net.callback.ISuccess;
import cn.fulushan.fuhttp.net.service.MyAppService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReTrofitRxJavaActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.quert_art_btn)
    Button quertArtBtn;
    @BindView(R.id.result_tv)
    TextView resultTv;
    MyAppService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        toolbarTitle.setText("ReTrofit+Rxjava");

        ivBack.setOnClickListener(this);
        quertArtBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ReTrofitRxJavaActivity.this.finish();
                break;
            case R.id.quert_art_btn:

                FuRestClient.builder()
                        .url("www.baidu.com")
                        .params("d","")
                        .iRequest(new IRequest() {
                            @Override
                            public void onResuestStart() {

                            }

                            @Override
                            public void onResuestEnd() {

                            }
                        }).iSuccess(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {

                            }
                        }).iFailure(new IFailure() {
                            @Override
                            public void onFailure() {

                            }
                        }).iError(new IError() {
                            @Override
                            public void onError(int code, String response) {

                            }
                        })
                        .build();

                break;
        }
    }
}
