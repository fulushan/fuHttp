package cn.fulushan.fuhttp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fulushan.fuhttp.R;
import cn.fulushan.fuhttp.bean.Article;
import cn.fulushan.fuhttp.config.Url;
import cn.fulushan.fuhttp.service.MyAppService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReTrofitActivity extends AppCompatActivity implements View.OnClickListener {


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

        toolbarTitle.setText("ReTrofit基本使用");

        ivBack.setOnClickListener(this);
        quertArtBtn.setOnClickListener(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         service = retrofit.create(MyAppService.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ReTrofitActivity.this.finish();
                break;
            case R.id.quert_art_btn:
                Call<Article> call =   service.listArt(0);
                call.enqueue(new Callback<Article>() {
                    @Override
                    public void onResponse(Call<Article> call, Response<Article> response) {
                        resultTv.setText(response.body().getData().getDatas().get(10).getTitle());
                    }
                    @Override
                    public void onFailure(Call<Article> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                break;
        }
    }
}
