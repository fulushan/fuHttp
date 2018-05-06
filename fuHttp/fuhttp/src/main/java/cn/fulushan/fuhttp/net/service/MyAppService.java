package cn.fulushan.fuhttp.net.service;

import cn.fulushan.fuhttp.bean.Article;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fulushan on 18/4/29.
 */

public interface MyAppService {

    @GET("article/list/{page}/json")
    Call<Article> listArt(@Path("page") int page);
}
