package cn.fulushan.fuhttp.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fulushan on 18/5/6.
 */

public class RequestCallBack implements Callback<String> {

    private  IRequest iRequest;
    private  ISuccess iSuccess;
    private  IFailure iFailure;
    private  IError iError;

    public RequestCallBack(IRequest iRequest, ISuccess iSuccess, IFailure iFailure, IError iError) {
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.iError = iError;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(iSuccess!=null&&call.isExecuted()){
                iSuccess.onSuccess(response.body());
            }
        }else{
            if(iError!=null){
                iError.onError(response.code(),response.message());
            }
        }
        onRequestFinish();
    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
       if(iFailure!=null){
           iFailure.onFailure();
       }
       if(iRequest!=null){
           iRequest.onResuestEnd();
       }
       onRequestFinish();
    }



    private void onRequestFinish() {

    }
}
