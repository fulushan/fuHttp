package cn.fulushan.fuhttp.net;

import java.util.Map;

import cn.fulushan.fuhttp.net.callback.IError;
import cn.fulushan.fuhttp.net.callback.IFailure;
import cn.fulushan.fuhttp.net.callback.IRequest;
import cn.fulushan.fuhttp.net.callback.ISuccess;
import cn.fulushan.fuhttp.net.callback.RequestCallBack;
import cn.fulushan.fuhttp.net.enums.HttpMethod;
import cn.fulushan.fuhttp.net.service.FuRestService;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by fulushan on 18/5/5.
 */

public class FuRestClient {



    private final String url;
    private final Map<String,Object> params;
    private final IRequest iRequest;
    private final ISuccess iSuccess;
    private final IFailure iFailure;
    private final IError iError;

    public FuRestClient(String url, Map<String, Object> params, IRequest iRequest, ISuccess iSuccess, IFailure iFailure, IError iError) {
        this.url = url;
        this.params = params;
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.iError = iError;
    }

    public static FuRestClientBuilder builder(){
        return new FuRestClientBuilder();
    }


    public void request(HttpMethod method){

        FuRestService restService = FuRestCreator.getFuRestService();
        Call<String> call = null;

        if(iRequest!=null){
            iRequest.onResuestStart();
        }

        switch (method){
            case GET:
                call = restService.get(url,params);
                break;
            case POST:
                break;
            case DELETE:
                break;
            case DOWNLOAD:
                break;
            case UPLOAD:
                break;
            default:
                break;
        }

        if(call!=null){
         call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallBack(iRequest,iSuccess,iFailure,iError);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
}
