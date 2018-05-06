package cn.fulushan.fuhttp.net;

import java.util.Map;

import cn.fulushan.fuhttp.net.callback.IError;
import cn.fulushan.fuhttp.net.callback.IFailure;
import cn.fulushan.fuhttp.net.callback.IRequest;
import cn.fulushan.fuhttp.net.callback.ISuccess;
import cn.fulushan.fuhttp.net.callback.RequestCallBack;
import cn.fulushan.fuhttp.net.enums.HttpMethod;
import cn.fulushan.fuhttp.net.service.FuRestService;
import okhttp3.RequestBody;
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
    private final RequestBody requestBody;

    public FuRestClient(String url, Map<String, Object> params, IRequest iRequest, ISuccess iSuccess, IFailure iFailure, IError iError,RequestBody requestBody) {
        this.url = url;
        this.params = params;
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.iError = iError;
        this.requestBody = requestBody;
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
                call = restService.post(url,params);
                break;
            case DELETE:
                call = restService.delete(url,params);
                break;
            case PUT:
                call = restService.put(url, params);
                break;
            case POST_RAW:
                call = restService.postRaw(url, requestBody);
                break;
            case PUT_RAW:
                call = restService.putRaw(url, requestBody);
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

    public final void post() {
        if (requestBody == null) {
            request(HttpMethod.POST);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (requestBody == null) {
            request(HttpMethod.PUT);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
//        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
//                SUCCESS, FAILURE, ERROR)
//                .handleDownload();
    }
}
