package cn.fulushan.fuhttp.service;

import cn.fulushan.fuhttp.bean.Article;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by fulushan on 18/4/29.
 */

public interface AppService {

    @GET("article/list/{page}/json")
    Observable<Article> listArt(@Path("page") int page);
}
