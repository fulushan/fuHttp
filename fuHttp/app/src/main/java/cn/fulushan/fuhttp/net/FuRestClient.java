package cn.fulushan.fuhttp.net;

import java.util.Map;

import cn.fulushan.fuhttp.net.callback.IError;
import cn.fulushan.fuhttp.net.callback.IFailure;
import cn.fulushan.fuhttp.net.callback.IRequest;
import cn.fulushan.fuhttp.net.callback.ISuccess;

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

}
