package cn.fulushan.fuhttp.net;

import java.util.concurrent.TimeUnit;

import cn.fulushan.fuhttp.net.service.FuRestService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by fulushan on 18/5/5.
 */

public class FuRestCreator {


    public static FuRestService getFuRestService(){
        return FuRestServiceHolder.FU_REST_SERVICE;
    }

    private static final class RetrofitHolder{

       private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
//                .baseUrl(Url.BASE_URL)
                .client(OkHttpHolder.client)
               .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;
        private static final  OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class FuRestServiceHolder{
        private static final FuRestService FU_REST_SERVICE  = RetrofitHolder.RETROFIT_CLIENT.create(FuRestService.class);
    }
}
