package cn.fulushan.fuhttp.net;

import java.util.Map;
import java.util.WeakHashMap;

import cn.fulushan.fuhttp.net.callback.IError;
import cn.fulushan.fuhttp.net.callback.IFailure;
import cn.fulushan.fuhttp.net.callback.IRequest;
import cn.fulushan.fuhttp.net.callback.ISuccess;

/**
 * Created by fulushan on 18/5/5.
 */

public class FuRestClientBuilder {


    private  String url;
    private  Map<String,Object> params;
    private  IRequest iRequest;
    private  ISuccess iSuccess;
    private  IFailure iFailure;
    private  IError iError;

    FuRestClientBuilder() {

    }

    public final FuRestClientBuilder url(String url){
        this.url =  url;
        return this;
    }
    public final FuRestClientBuilder params(Map<String,Object> params){
        this.params =  params;
        return this;
    }
    public final FuRestClientBuilder params(String param,Object value){
        if(this.params == null){
            this.params = new WeakHashMap<>();
        }
        this.params.put(param,value);
        return this;
    }

    public final FuRestClientBuilder iRequest(IRequest iRequest){
        this.iRequest =  iRequest;
        return this;
    }
    public final FuRestClientBuilder iSuccess(ISuccess iSuccess){
        this.iSuccess = iSuccess;
        return this;
    }
    public final FuRestClientBuilder iFailure(IFailure iFailure){
        this.iFailure =  iFailure;
        return this;
    }
    public final FuRestClientBuilder iError(IError iError){
        this.iError =  iError;
        return this;
    }

    public final FuRestClient build(){
        return new FuRestClient(url,params,iRequest,iSuccess,iFailure,iError);
    }

}
